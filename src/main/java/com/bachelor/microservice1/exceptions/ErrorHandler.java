package com.bachelor.microservice1.exceptions;

import com.bachelor.microservice1.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.bachelor.microservice1.exceptions.ErrorMessageConstants.*;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({GymAlreadyExists.class})
    protected ResponseEntity<Object> handleGymAlreadyExists(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse(GYM_ALREADY_EXISTS, null, HttpStatus.CONFLICT.value()), null, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({GymDoesNotExist.class})
    protected ResponseEntity<Object> handleGymDoesNotExist(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse(GYM_NOT_FOUND, null, HttpStatus.NOT_FOUND.value()), null, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({OfferForThisGymAlreadyExists.class})
    protected ResponseEntity<Object> handleOfferForThisGymAlreadyExists(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse(OFFER_ALREADY_EXISTS, null, HttpStatus.CONFLICT.value()), null, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({OfferNotFound.class})
    protected ResponseEntity<Object> handleOfferNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, new HttpResponse(OFFER_NOT_FOUND, null, HttpStatus.NOT_FOUND.value()), null, HttpStatus.NOT_FOUND, request);
    }
}
