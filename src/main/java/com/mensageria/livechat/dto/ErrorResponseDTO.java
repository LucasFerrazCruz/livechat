package com.mensageria.livechat.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponseDTO {
    
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String timestamp;
    @Getter
    @Setter
    private Map<String, String> fieldErrors;

    public ErrorResponseDTO (String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
        this.fieldErrors = new HashMap<>();
    }

    public void addFieldErrors (String field, String error) {
        this.fieldErrors.put(field, error);
    }
}
