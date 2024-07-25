package com.baridmedia.service.impl;

import com.baridmedia.dto.response.DocumentResponse;
import com.baridmedia.exception.DocumentNotFoundException;
import com.baridmedia.exception.DocumentSignException;
import com.baridmedia.repository.DocumentRepository;
import com.baridmedia.service.DocumentService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.util.Base64;
import java.util.UUID;


@Slf4j
@NoArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    @Value("${minio.bucket-name}")
    private String bucketName;
    private final static String SIGNATURE = "\n\nSignature:\n";
    private final static String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private final static String SIGNATURE_DOC_NAME_PREFIX = "signed-";
    private DocumentRepository documentRepository;
    private MinioClient minioClient;




    @Override
    public DocumentResponse uploadDocument(MultipartFile file) {
        return null;
    }

    @Override
    public Page<DocumentResponse> getDocuments(Pageable pageable) {
        //TODO: filter by clientId
        return this.documentRepository.findAll(pageable)
                .map(DocumentResponse::fromEntity);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public DocumentResponse sign(UUID documentId) {
      try {
          var document = documentRepository.findById(documentId).orElseThrow(() -> new DocumentNotFoundException(documentId));
          var originalFileName = document.getFileName();
          var originalFileBytes = minioClient
                  .getObject(GetObjectArgs.builder().bucket(bucketName).object(originalFileName).build())
                  .readAllBytes();
          var keyPair = generateKeyPair();
          var signatureBytes = signData(originalFileBytes, keyPair.getPrivate());
          var outputStream = new ByteArrayOutputStream();
          outputStream.write(originalFileBytes);
          outputStream.write(SIGNATURE.getBytes());
          outputStream.write(Base64.getEncoder().encode(signatureBytes));

          var signedFilename =  SIGNATURE_DOC_NAME_PREFIX.concat(originalFileName);
          minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(signedFilename).stream(new ByteArrayInputStream(outputStream.toByteArray()), outputStream.size(), -1).build());

          document.setFileName(signedFilename);
          document.setUrl("/minio/" + bucketName + "/" + signedFilename);
          document.setSigned(Boolean.TRUE);

          return DocumentResponse.fromEntity(documentRepository.save(document));
      }catch (Exception e){
          throw new DocumentSignException(documentId);
      }
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private byte[] signData(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }


    @Override
    public Boolean verifyDocument(UUID documentId) {
        var document = documentRepository.findById(documentId).orElseThrow(() -> new DocumentNotFoundException(documentId));
        return document.isSigned();
    }
}
