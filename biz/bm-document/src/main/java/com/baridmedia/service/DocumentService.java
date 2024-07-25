package com.baridmedia.service;

import com.baridmedia.dto.response.DocumentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface DocumentService {
    DocumentResponse uploadDocument(MultipartFile file);
    Page<DocumentResponse> getDocuments(Pageable pageable);
    DocumentResponse sign(UUID documentId);
    Boolean verifyDocument(UUID documentId);
}
