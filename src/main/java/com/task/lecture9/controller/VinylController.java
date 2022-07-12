package com.task.lecture9.controller;

import com.task.lecture9.domain.service.VinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VinylController {
    @Autowired
    VinylService vinylService;

    @GetMapping(value = {"vinyls", "/vinyls/{id}"})
    public Object getVinylById(@PathVariable(required = false) Integer id) throws Exception {
        try {
            if (id == null) {
                return vinylService.findAll();

            } else if (id <= 0 || id > 3) {
                throw new Exception("ID(1~3)を入力してください");

            } else if (id > 1 || id < 4) {
                return vinylService.findById(id);

            }
        } catch (Exception e) {
            return "入力に誤りがあります。" + e.getMessage();
        }

        return null;
    }
}