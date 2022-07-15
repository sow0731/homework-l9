package com.task.lecture9.controller;

import com.task.lecture9.domain.service.VinylService;
import com.task.lecture9.domain.service.VinylServiceImpl;
import com.task.lecture9.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
public class VinylController {
    private final VinylService vinylService;

    public VinylController(VinylServiceImpl vinylServiceImpl) {
        this.vinylService = vinylServiceImpl;
    }

    @GetMapping(value = {"vinyls", "/vinyls/{id}"})
    public Object getVinylById(@PathVariable(required = false) Integer id) throws Exception {
        if (id == null) {
            return vinylService.findAll();
        }
        return vinylService.findVinyl(id);
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {

        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());

        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }
}