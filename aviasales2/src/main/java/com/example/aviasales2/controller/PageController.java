package com.example.aviasales2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 10000)
@Controller
public class PageController {

    @GetMapping("/international")
    public ResponseEntity <?>
    getInternationalPage(@RequestParam String lang) throws Exception {
        if (lang.equals("en.json")) {
            Path jsonPath = Paths.get("aviasales2\\src\\main\\resources\\en.json");
            byte[] jsonEng = Files.readAllBytes(jsonPath);
            return new ResponseEntity <>(jsonEng, HttpStatus.OK);
        }
        if (lang.equals("ru.json")) {
            Path jsonPath = Paths.get("aviasales2\\src\\main\\resources\\ru.json");
            byte[] jsonRus = Files.readAllBytes(jsonPath);
            return new ResponseEntity <>(jsonRus, HttpStatus.OK);
        }
        throw new Exception();
    }
}
