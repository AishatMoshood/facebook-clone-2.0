package com.aishatmoshood.facebookclone.exceptions;

import com.aishatmoshood.facebookclone.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(EmailNotValidException.class)
    public ResponseEntity<ErrorMessageDto> emailNotValidException(EmailNotValidException ex, WebRequest request){
        ErrorMessageDto message = new ErrorMessageDto(HttpStatus.NOT_ACCEPTABLE,ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(message);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessageDto> userAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request){
        ErrorMessageDto message = new ErrorMessageDto(HttpStatus.ALREADY_REPORTED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(message);
    }
}
