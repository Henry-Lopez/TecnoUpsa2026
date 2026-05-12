package com.aguafutura.platform.evidence.application.port;

import java.io.InputStream;
import java.util.UUID;

public interface EvidenceStoragePort {
    /**
     * Guarda el archivo y devuelve la ruta relativa o absoluta donde se guardó.
     */
    String saveFile(UUID tenantId, String originalFilename, InputStream content);
}
