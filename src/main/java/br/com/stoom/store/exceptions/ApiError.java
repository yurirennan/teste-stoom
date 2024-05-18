package br.com.stoom.store.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class ApiError {
    private String resume;
    private HttpStatus status;
    private List<String> errors;

    public ApiError(final HttpStatus status, final String resume, final String error) {
        this.status = status;
        this.resume = resume;
        this.errors = Collections.singletonList(error);
    }

    public ApiError(final HttpStatus status, final String resume, final List<String> errors) {
        this.status = status;
        this.resume = resume;
        this.errors = errors;
    }

    public ApiError() {
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
