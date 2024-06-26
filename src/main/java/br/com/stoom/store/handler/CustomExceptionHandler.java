package br.com.stoom.store.handler;

import br.com.stoom.store.exceptions.ApiError;
import br.com.stoom.store.exceptions.brand.BrandNotExistsException;
import br.com.stoom.store.exceptions.brand.BrandNotFoundException;
import br.com.stoom.store.exceptions.category.CategoryNotExistsException;
import br.com.stoom.store.exceptions.category.CategoryNotFoundException;
import br.com.stoom.store.exceptions.product.ProductNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApiError apiErrorMessage = new ApiError(status, ex.toString(), errors);

        return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
    }

    @ExceptionHandler({
            ProductNotFoundException.class,
            CategoryNotFoundException.class,
            BrandNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFoundException(final RuntimeException exception) {

        ApiError apiErrorMessage = new ApiError(HttpStatus.NOT_FOUND, exception.toString(), exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

    @ExceptionHandler({
            CategoryNotExistsException.class,
            BrandNotExistsException.class})
    public ResponseEntity<ApiError> handleNotExistsException(final RuntimeException exception) {

        ApiError apiErrorMessage = new ApiError(HttpStatus.BAD_REQUEST, exception.toString(), exception.getMessage());

        return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
    }

}
