package osu.damek.usedcars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionContoller {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentIOException(HttpServletRequest request, IllegalArgumentException ex) {
        log.error("Request " + request.getRequestURL() + " threw an IllegalArgumentIOException! " + ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                "Object wasn't found (may not exist), " + request.getRequestURL() + " " + "threw an IllegalArgumentIOException! " + ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleIllegalArgumentException(HttpServletRequest request, Exception ex) {
        log.error("Request " + request.getRequestURL() + " threw an IllegalArgumentException! " + ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Something went wrong (bad requests), " + request.getRequestURL() + " " + "threw an IllegalArgumentException! " + ex.getMessage());
    }
}
