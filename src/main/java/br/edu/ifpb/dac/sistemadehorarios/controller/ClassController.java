package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.DTO.ClassDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService service;

    @PostMapping
    public ResponseEntity<ClassDTO> create(@RequestBody ClassModel classModel){
        boolean result = this.service.create(classModel);
        if(result) {
            return ResponseEntity.status(201).body(new ClassDTO(classModel));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity <List<ClassDTO>> read(){
        List<ClassModel> result = this.service.read();
        return ResponseEntity.status(200).body(ClassDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ClassDTO> readByUuid(@PathVariable("uuid") String uuid) {
        ClassModel result = this.service.readByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ClassDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<ClassDTO> update(@RequestBody ClassModel classModel, @PathVariable("uuid") String uuid){
        boolean result = this.service.update(classModel, uuid);
        if (result) {
            return ResponseEntity.status(200).body(new ClassDTO(classModel));
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
        boolean result = this.service.delete(uuid);
        if (result) {
            return ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");

    }
}
