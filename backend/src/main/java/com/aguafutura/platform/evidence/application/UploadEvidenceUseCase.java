package com.aguafutura.platform.evidence.application;

import com.aguafutura.platform.evidence.application.port.EvidenceRepositoryPort;
import com.aguafutura.platform.evidence.application.port.EvidenceStoragePort;
import com.aguafutura.platform.evidence.domain.Evidence;
import com.aguafutura.platform.evidence.domain.ReferenceType;

import java.io.InputStream;
import java.util.UUID;

public class UploadEvidenceUseCase {

    private final EvidenceRepositoryPort repository;
    private final EvidenceStoragePort storage;

    public UploadEvidenceUseCase(EvidenceRepositoryPort repository, EvidenceStoragePort storage) {
        this.repository = repository;
        this.storage = storage;
    }

    public Evidence execute(
            UUID tenantId,
            ReferenceType referenceType,
            UUID referenceId,
            String fileName,
            String contentType,
            InputStream content
    ) {
        // 1. Save file to storage
        String filePath = storage.saveFile(tenantId, fileName, content);

        // 2. Save metadata to DB
        Evidence evidence = Evidence.create(
                tenantId,
                referenceType,
                referenceId,
                fileName,
                contentType,
                filePath
        );

        return repository.save(evidence);
    }
}
