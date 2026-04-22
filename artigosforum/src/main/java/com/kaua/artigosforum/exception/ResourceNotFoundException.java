package com.kaua.artigosforum.exception;

import jakarta.annotation.Resource;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
