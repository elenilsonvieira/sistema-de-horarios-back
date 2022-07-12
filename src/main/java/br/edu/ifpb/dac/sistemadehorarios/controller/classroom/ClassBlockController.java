package br.edu.ifpb.dac.sistemadehorarios.controller.classroom;

import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassBlockDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassBlockInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.service.classroom.ClassBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classBlock")
public class ClassBlockController {

    @Autowired
    private ClassBlockService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ClassBlockModel classBlockModel) throws ClassBlockInvalidException {
        boolean result = this.service.create(classBlockModel);
        if(result) {
            return ResponseEntity.status(201).body(new ClassBlockDTO(classBlockModel));
        }
        return ResponseEntity.status(400).body("Inválido");

    }

    @GetMapping
    public ResponseEntity <List<ClassBlockDTO>> read(){
        List<ClassBlockModel> result = this.service.read();
        return ResponseEntity.status(200).body(ClassBlockDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ClassBlockDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ClassBlockModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ClassBlockDTO(result));
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
