package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.LessonDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService service;

    @PostMapping
    public ResponseEntity<LessonDTO> create(@RequestBody LessonDRO lessonModel){
        LessonModel result = this.service.create(lessonModel);
        if(result != null) {
            return ResponseEntity.status(201).body(new LessonDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity <List<LessonDTO>> read(){
        List<LessonModel> result = this.service.read();
        return ResponseEntity.status(200).body(LessonDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<LessonDTO> findByUuid(@PathVariable("uuid") String uuid) {
        LessonModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new LessonDTO(result));
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
