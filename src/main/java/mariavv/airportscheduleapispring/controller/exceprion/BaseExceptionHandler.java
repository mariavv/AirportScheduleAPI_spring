package mariavv.airportscheduleapispring.controller.exceprion;

import mariavv.airportscheduleapispring.domain.dto.response.ErrorInfoResponse;
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
    protected ResponseEntity<ErrorInfoResponse> handleNotFoundException(Exception ex) {
        return new ResponseEntity<>(new ErrorInfoResponse(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorInfoResponse> handleIllegalArgumentException() {
        return new ResponseEntity<>(new ErrorInfoResponse("Operation not performed"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = PasswordNotMatchesException.class)
    public ResponseEntity<ErrorInfoResponse> handlePasswordNotMatchesException() {
        return new ResponseEntity<>(new ErrorInfoResponse("Password not matches"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserExistAlready.class)
    public ResponseEntity<ErrorInfoResponse> handleUserExistAlready() {
        return new ResponseEntity<>(new ErrorInfoResponse("User with this name exist already"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorInfoResponse> handleRuntimeException(Exception ex) {
        return new ResponseEntity<>(new ErrorInfoResponse(ex.getMessage()),
                HttpStatus.MULTI_STATUS);
    }
}