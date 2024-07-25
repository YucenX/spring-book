package com.farms4life2016.chapter07;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * very vanilla exception class that represents a failure to find 
 * an artist within our database.
 * Note that we use ResponseStatus to map this exception to an error 404 HTTP response
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(String e) {
        super(e);
    }

    public CandidateNotFoundException(Exception e) {
        super(e);
    }
}