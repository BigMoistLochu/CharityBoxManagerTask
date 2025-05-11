package application.charityboxmanager.exception;

import application.charityboxmanager.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectionBoxNotFoundException.class)
    public ResponseEntity<String> handleCollectionBoxNotFound(CollectionBoxNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FundraisingEventNotFoundException.class)
    public ResponseEntity<String> handleFundraisingEventNotFound(FundraisingEventNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCurrencyException.class)
    public ResponseEntity<String> handleInvalidCurrency(InvalidCurrencyException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CollectionBoxNotEmptyException.class)
    public ResponseEntity<String> handleBoxNotEmpty(CollectionBoxNotEmptyException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CollectionBoxAlreadyAssignedException.class)
    public ResponseEntity<String> handleBoxAlreadyAssigned(CollectionBoxAlreadyAssignedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(FundraisingEventAlreadyAssignedException.class)
    public ResponseEntity<String> handleEventAlreadyAssigned(FundraisingEventAlreadyAssignedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
