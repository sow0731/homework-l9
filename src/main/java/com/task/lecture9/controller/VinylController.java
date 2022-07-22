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
import com.task.lecture9.domain.model.Vinyl;
import com.task.lecture9.domain.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VinylController {
    @Autowired
    VinylService vinylService;


    public VinylController(VinylServiceImpl vinylServiceImpl) {
        this.vinylService = vinylServiceImpl;
    }

    @GetMapping("/vinyls")
    public Object getVinyls() {


    @GetMapping("/vinyl-all")
    public List<Vinyl> getVinyl() {

        return vinylService.findAll();
    }

    @GetMapping("/vinyl-by-id")
    public Object getVinylById(@RequestParam(name = "vinyl") Integer id) throws Exception {
        try {
            if (id <= 0 || id > 3) {
                throw new Exception("ID(1~3)を入力してください");

    @PostMapping("/insert_vinyls")
    public String create(@Validated @RequestBody CreateForm form) {
        vinylService.insert(form);
        return "New Vinyl Data Is Added";
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {
            } else if (id > 1 || id < 4) {
                return vinylService.findById(id);
            }
        } catch (Exception e) {
            return "入力に誤りがあります。" + e.getMessage();
        }

        return null;
    }
}