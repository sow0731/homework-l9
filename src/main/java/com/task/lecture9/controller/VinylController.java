package com.task.lecture9.controller;

import com.task.lecture9.domain.Form.CreateForm;
import com.task.lecture9.domain.service.VinylService;
import com.task.lecture9.domain.service.VinylServiceImpl;
import com.task.lecture9.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VinylController {
    private final VinylService vinylService;

    public VinylController(VinylServiceImpl vinylServiceImpl) {
        this.vinylService = vinylServiceImpl;
    }

    @GetMapping("/vinyls")
    public Object getVinyls() {

        return vinylService.findAll();
    }

    @GetMapping("/vinyls/{id}")
    public Object getVinylById(@Validated @PathVariable(required = false) Integer id) throws Exception {
        return vinylService.findById(id);
    }

     @PostMapping("/vinyls")
    public String insertVinylData(@Validated @RequestBody InsertForm insertForm) {
        VinylDto vinylDto = new VinylDto();

        vinylDto.setTitle((insertForm.getTitle()));
        vinylDto.setArtist(insertForm.getArtist());
        vinylDto.setLabel(insertForm.getLabel());
        vinylDto.setRelease_year(insertForm.getRelease_year());

        vinylService.insert(vinylDto);

        return "New Vinyl Data Is Added";
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