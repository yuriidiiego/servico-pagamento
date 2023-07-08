package br.org.portalfadesp.servicopagamento.domain.pagamento.exception;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PagamentoExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public PagamentoExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatus status,
    WebRequest request
  ) {
    List<ErrorResponse> errorResponses = new ArrayList<>();
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    fieldErrors.forEach(fieldError -> {
      String message = messageSource.getMessage(
        fieldError,
        LocaleContextHolder.getLocale()
      );
      ErrorResponse errorResponse = new ErrorResponse(
        message,
        HttpStatus.BAD_REQUEST.value(),
        ((ServletWebRequest) request).getRequest().getRequestURI()
      );
      errorResponses.add(errorResponse);
    });
    return new ResponseEntity<>(errorResponses, HttpStatus.BAD_REQUEST);
  }
  
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
          HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorResponse errorResponse = new ErrorResponse(
      ex.getMessage(),
      HttpStatus.BAD_REQUEST.value(),
      ((ServletWebRequest) request).getRequest().getRequestURI()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PagamentoInvalidException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ErrorResponse handlePagamentoInvalidException(
    PagamentoInvalidException ex,
    HttpServletRequest request
  ) {
    String message = ex.getMessage();
    int statusCode = HttpStatus.CONFLICT.value();
    String path = request.getRequestURI();

    return new ErrorResponse(message, statusCode, path);
  }

  @ExceptionHandler(PagamentoNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handlePagamentoNotFoundException(
    PagamentoNotFoundException ex,
    HttpServletRequest request
  ) {
    String message = ex.getMessage();
    int statusCode = HttpStatus.NOT_FOUND.value();
    String path = request.getRequestURI();

    return new ErrorResponse(message, statusCode, path);
  }
}
