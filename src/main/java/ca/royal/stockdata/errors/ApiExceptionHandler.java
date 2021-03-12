package ca.royal.stockdata.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String RUNTIME_ERROR = "Processing_Error";

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ApiErrorResponse> handleRuntimeError(RuntimeException ex) {
        ApiErrorResponse response = new ApiErrorResponse(RUNTIME_ERROR, ex.getMessage(), "");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
