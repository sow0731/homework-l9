package com.task.lecture9.controller;

import com.task.lecture9.dto.VinylDto;
import com.task.lecture9.form.InsertForm;
import com.task.lecture9.form.ValidationGroupAll;
import com.task.lecture9.repository.entity.Vinyl;
import com.task.lecture9.service.VinylService;
import com.task.lecture9.service.VinylServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class VinylController {
    private final VinylService vinylService;

    public VinylController(VinylServiceImpl vinylServiceImpl) {
        this.vinylService = vinylServiceImpl;
    }

    @GetMapping("/vinyls")
    public List<Vinyl> getVinyls() {

        return vinylService.findAll();
    }

    @GetMapping("/vinyls/{id}")
    public Object getVinylById(@PathVariable(required = false) Integer id) throws Exception {
        return vinylService.findById(id);
    }

    @PostMapping("/vinyls")
    public ResponseEntity<Map> create(
            @Validated(ValidationGroupAll.class) @RequestBody InsertForm insertForm,
            HttpServletRequest request) {

        VinylDto vinylDto = new VinylDto(
                insertForm.getTitle(),
                insertForm.getArtist(),
                insertForm.getLabel(),
                insertForm.getReleaseYear());

        int id = vinylService.insert(vinylDto);

        URI uri = ServletUriComponentsBuilder.fromContextPath(request).path("/vinyls/" + id).build().toUri();
        return ResponseEntity.created(uri).body(Map.of("message", "New Vinyl Data Is Added"));
    }
}