package com.task.lecture9.controller;

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

    @GetMapping("/vinyl-all")
    public List<Vinyl> getVinyl() {
        return vinylService.findAll();
    }

    @GetMapping("/vinyl-by-id")
    public Object getVinylById(@RequestParam(name = "vinyl") Integer id) throws Exception {
        try {
            if (id <= 0 || id > 3) {
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