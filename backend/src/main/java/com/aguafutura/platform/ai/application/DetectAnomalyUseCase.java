package com.aguafutura.platform.ai.application;

import com.aguafutura.platform.ai.domain.AnomalyReport;
import com.aguafutura.platform.consumption.application.port.ConsumptionRepositoryPort;
import com.aguafutura.platform.consumption.domain.Consumption;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DetectAnomalyUseCase {

    private final ChatModel chatModel;
    private final ConsumptionRepositoryPort consumptionRepository;

    public DetectAnomalyUseCase(ChatModel chatModel, ConsumptionRepositoryPort consumptionRepository) {
        this.chatModel = chatModel;
        this.consumptionRepository = consumptionRepository;
    }

    public AnomalyReport execute(UUID tenantId, UUID assetId) {
        // 1. Fetch recent consumptions for the asset
        List<Consumption> consumptions = consumptionRepository.findByTenantIdAndAssetId(tenantId, assetId);
        
        if (consumptions.isEmpty()) {
            return new AnomalyReport(false, "No hay consumos registrados para analizar.", "Registrar más datos.");
        }

        // Sort descending by date and limit to 5
        List<Consumption> recent = consumptions.stream()
                .sorted((c1, c2) -> c2.getReadingDate().compareTo(c1.getReadingDate()))
                .limit(5)
                .collect(Collectors.toList());

        String dataString = recent.stream()
                .map(c -> "Fecha: " + c.getReadingDate().toString() + " - Valor: " + c.getValue() + " " + c.getUnit())
                .collect(Collectors.joining("\n"));

        // 2. Build the prompt
        String systemMessage = """
                Eres un experto analista de infraestructuras de agua.
                Tu tarea es analizar los siguientes consumos recientes de un medidor de agua y determinar si existe una anomalía (ej. una posible fuga o un medidor roto).
                Si el último consumo es excesivamente más alto (o más bajo) que el promedio histórico, debes marcarlo como anomalía.
                
                Responde EXCLUSIVAMENTE en el siguiente formato (sin markdown ni texto extra):
                ANOMALY_DETECTED=true/false
                ANALYSIS=tu justificación breve
                RECOMMENDATION=tu recomendación
                """;

        String userMessage = "Consumos recientes:\n" + dataString;

        // 3. Call the AI
        try {
            String response = chatModel.call(systemMessage + "\n" + userMessage);
            return parseResponse(response);
        } catch (Exception e) {
            // Fallback in case API key is missing or invalid
            return simulateAnomalyDetection(recent);
        }
    }

    private AnomalyReport parseResponse(String aiResponse) {
        boolean isAnomaly = false;
        String analysis = "Análisis no disponible";
        String recommendation = "Revisión manual recomendada";

        String[] lines = aiResponse.split("\n");
        for (String line : lines) {
            if (line.startsWith("ANOMALY_DETECTED=")) {
                isAnomaly = Boolean.parseBoolean(line.substring("ANOMALY_DETECTED=".length()).trim());
            } else if (line.startsWith("ANALYSIS=")) {
                analysis = line.substring("ANALYSIS=".length()).trim();
            } else if (line.startsWith("RECOMMENDATION=")) {
                recommendation = line.substring("RECOMMENDATION=".length()).trim();
            }
        }

        return new AnomalyReport(isAnomaly, analysis, recommendation);
    }

    private AnomalyReport simulateAnomalyDetection(List<Consumption> recent) {
        if (recent.size() < 2) {
            return new AnomalyReport(false, "Insuficientes datos para simulación.", "N/A");
        }
        
        double latest = recent.get(0).getValue().doubleValue();
        double previous = recent.get(1).getValue().doubleValue();

        if (latest > previous * 1.5) {
            return new AnomalyReport(true, "[SIMULADO] El consumo subió más de un 50%. (Llave de OpenAI no configurada)", "Enviar un técnico para inspeccionar posibles fugas.");
        }

        return new AnomalyReport(false, "[SIMULADO] Consumo dentro del rango normal. (Llave de OpenAI no configurada)", "Ninguna acción requerida.");
    }
}
