package br.com.stoom.store.exceptions.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotExistsException extends RuntimeException {

    public CategoryNotExistsException(String message) {
        super(message);
    }

}
