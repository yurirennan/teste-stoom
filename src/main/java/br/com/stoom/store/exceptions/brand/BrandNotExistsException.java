package br.com.stoom.store.exceptions.brand;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BrandNotExistsException extends RuntimeException {
    public BrandNotExistsException(String message) {
        super(message);
    }

}
