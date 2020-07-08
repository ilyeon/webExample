package com.example.website.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Login Id is duplicated")
public class DuplicateLoginIdException extends RuntimeException{
    
}