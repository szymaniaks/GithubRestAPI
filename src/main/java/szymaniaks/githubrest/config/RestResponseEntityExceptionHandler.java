package szymaniaks.githubrest.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import szymaniaks.githubrest.exceptions.RepositoryNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RepositoryNotFoundException.class)
    protected ResponseEntity<Object> handleRepositoryNotFound(RepositoryNotFoundException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage() ,new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
