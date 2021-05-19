package com.srezz.exception.advicecontroller;

import com.srezz.controllers.FindController;
import com.srezz.controllers.SaveController;
import com.srezz.controllers.UpdateController;
import com.srezz.exception.GroupNotFoundException;
import com.srezz.exception.InvalidInputDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.srezz.utils.ResponseHeaders.APPLICATION_JSON_WITH_CHARSET;
import static com.srezz.utils.ResponseHeaders.HEADER_CONTENT_TYPE;

@Slf4j
@ControllerAdvice(basePackageClasses = {FindController.class, SaveController.class, UpdateController.class})
public class AdviceController {

    @ExceptionHandler(value = GroupNotFoundException.class)
    public ResponseEntity<Object> handleInvalidUserData(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }

    @ExceptionHandler(value = InvalidInputDataException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(InvalidInputDataException e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HEADER_CONTENT_TYPE, APPLICATION_JSON_WITH_CHARSET)
                .body(e.getMessage());
    }
}
