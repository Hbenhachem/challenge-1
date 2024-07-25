package com.baridmedia.exception;

import java.util.UUID;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(UUID id){
        super("Document not found: "+ id.toString());
    }
}
