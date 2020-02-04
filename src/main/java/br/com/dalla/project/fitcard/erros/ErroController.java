package br.com.dalla.project.fitcard.erros;


import br.com.dalla.project.fitcard.erros.tipos.BaseException;
import br.com.dalla.project.fitcard.erros.tipos.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
@ControllerAdvice
public class ErroController {

    static final Logger LOGGER = LogManager.getLogger(ErroController.class.getName());

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorDetails> authExceptionHandler(BaseException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMensagem(), e.getTipo(), e.getCodigo());
        return ResponseEntity.status(e.getCodigo()).body(errorDetails);
    }

    @ExceptionHandler(value = IOException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> ioExceptionHandler(IOException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), "Internal Server Error", 500);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), "Illegal Argument", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDetails> httpMessageNotReadableExceptionHandler(
            HttpMessageNotReadableException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), "Http Message Not Readable", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e) {
        FieldError firstError = e.getBindingResult().getFieldErrors().get(0);
        String errorMessage = firstError.getDefaultMessage();
        ErrorDetails errorDetails = new ErrorDetails(errorMessage, "Bad Request", 400);
        return ResponseEntity.status(errorDetails.getCodigo()).body(errorDetails);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDetails> runtimeExceptionnHandler(
            RuntimeException e) {
        e.printStackTrace();
        String errorMessage = e.getMessage();
        ErrorDetails errorDetails = new ErrorDetails(errorMessage, "Internal Server Error", 500);
        return ResponseEntity.status(errorDetails.getCodigo()).body(errorDetails);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(
            Exception e) {
        e.printStackTrace();
        String errorMessage = e.getMessage();
        ErrorDetails errorDetails = new ErrorDetails(errorMessage, "Internal Server Error", 500);
        return ResponseEntity.status(errorDetails.getCodigo()).body(errorDetails);
    }

}