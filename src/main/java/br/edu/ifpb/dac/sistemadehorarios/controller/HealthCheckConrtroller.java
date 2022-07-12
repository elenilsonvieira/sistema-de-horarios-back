package br.edu.ifpb.dac.sistemadehorarios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckConrtroller {

    @GetMapping
    public ResponseEntity<String> check(){
        return ResponseEntity.status(200).body("OK");
    }
}
