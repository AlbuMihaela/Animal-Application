package com.sda.project.controller.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String detailMessage) {
        super(detailMessage);
    }

}
