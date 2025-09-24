package de.szut.webshop.exceptions;


import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("Resource not found: " + message);
    }

    public ResourceNotFoundException(Long id) {
        this("ID " + id);
    }
}
