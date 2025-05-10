package tsvetomir.carfixshop.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String detailedMessage = ex.getMostSpecificCause().getMessage();

        String violatedField = extractFieldFromMessage(detailedMessage);

        String errorMessage;
        if (violatedField != null) {
            errorMessage = String.format("The value for '%s' already exists or violates a constraint. Please enter a different one.", violatedField);
        } else {
            errorMessage = "A data integrity violation occurred. Please check your input.";
        }

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return ResponseEntity.badRequest().body("Validation failed: " + message);

    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<String> handleTransactionSystemException(TransactionSystemException ex) {
        String message = "There was an issue saving your data. Make sure all required fields are filled.";
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());

    }

    private String extractFieldFromMessage(String message) {
        if (message == null) return null;
        if (message.contains("constraint")) {
            int start = message.indexOf("constraint \"") + 12;
            int end = message.indexOf("\"", start);
            if (start > 0 && end > start) {
                String constraint = message.substring(start, end);
                if (constraint.contains("_")) {
                    String[] parts = constraint.split("_");
                    return parts.length > 1 ? parts[1] : constraint;
                }
                return constraint;
            }
        }

        return null;
    }

}