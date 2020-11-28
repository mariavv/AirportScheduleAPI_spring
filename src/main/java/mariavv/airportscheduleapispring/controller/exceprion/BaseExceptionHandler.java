package mariavv.airportscheduleapispring.controller.exceprion;

import mariavv.airportscheduleapispring.domain.dto.ErrorInfoDto;
import mariavv.airportscheduleapispring.exception.NotFoundException;
import mariavv.airportscheduleapispring.exception.PasswordNotMatchesException;
import mariavv.airportscheduleapispring.exception.UserExistAlready;
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

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorInfoDto> handleIllegalArgumentException() {
        return new ResponseEntity<>(new ErrorInfoDto("Operation not performed"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = PasswordNotMatchesException.class)
    public ResponseEntity<ErrorInfoDto> handlePasswordNotMatchesException() {
        return new ResponseEntity<>(new ErrorInfoDto("Password not matches"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserExistAlready.class)
    public ResponseEntity<ErrorInfoDto> handleUserExistAlready() {
        return new ResponseEntity<>(new ErrorInfoDto("User with this name exist already"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorInfoDto> handleRuntimeException() {
        return new ResponseEntity<>(new ErrorInfoDto("Unknown error"),
                HttpStatus.MULTI_STATUS);
    }
}