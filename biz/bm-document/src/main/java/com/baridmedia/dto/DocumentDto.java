package com.baridmedia.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class DocumentDto {
    private String fileName;
    private String url;
    private boolean signed;
    private String signedUrl;
    private UUID clientId;
}
