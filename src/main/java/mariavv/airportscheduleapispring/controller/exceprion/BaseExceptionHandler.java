package mariavv.airportscheduleapispring.controller.exceprion;

import mariavv.airportscheduleapispring.domain.dto.ErrorInfoDto;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorInfoDto> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorInfoDto(e.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}