package com.baridmedia.exception;

import java.util.UUID;

public class DocumentSignException extends  RuntimeException{
    public DocumentSignException(UUID id){
        super("Document not signed :  "+ id.toString());
    }
}
