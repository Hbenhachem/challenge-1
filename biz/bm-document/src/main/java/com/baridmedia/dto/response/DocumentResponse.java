package com.baridmedia.dto.response;

import com.baridmedia.dto.DocumentDto;
import com.baridmedia.entity.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@SuperBuilder
public class DocumentResponse extends DocumentDto {
    public static DocumentResponse fromEntity(Document document){
        return DocumentResponse.builder()
                .fileName(document.getFileName())
                .url(document.getUrl())
                .signed(document.isSigned())
                .signedUrl(document.getSignedUrl())
                .clientId(document.getClientId())
                .build();
    }
}
