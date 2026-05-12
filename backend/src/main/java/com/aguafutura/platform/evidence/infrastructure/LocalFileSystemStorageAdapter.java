package com.aguafutura.platform.evidence.infrastructure;

import com.aguafutura.platform.evidence.application.port.EvidenceStoragePort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class LocalFileSystemStorageAdapter implements EvidenceStoragePort {

    private final Path rootLocation;

    public LocalFileSystemStorageAdapter() {
        // En un caso real, esto vendría de application.properties
        this.rootLocation = Paths.get("uploads");
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage location", e);
        }
    }

    @Override
    public String saveFile(UUID tenantId, String originalFilename, InputStream content) {
        try {
            if (originalFilename == null || originalFilename.isBlank()) {
                throw new IllegalArgumentException("Filename cannot be empty");
            }
            
            // Generar un nombre único para evitar colisiones
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + extension;
            
            // Crear carpeta del tenant si no existe
            Path tenantFolder = this.rootLocation.resolve(tenantId.toString());
            Files.createDirectories(tenantFolder);
            
            Path destinationFile = tenantFolder.resolve(Paths.get(uniqueFileName))
                    .normalize().toAbsolutePath();
            
            if (!destinationFile.getParent().equals(tenantFolder.toAbsolutePath())) {
                // Secuestro de path (Directory traversal)
                throw new SecurityException("Cannot store file outside current directory.");
            }
            
            Files.copy(content, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            
            // Retorna la ruta relativa para guardarla en la BD
            return "uploads/" + tenantId.toString() + "/" + uniqueFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}
