package br.edu.ifpb.dac.sistemadehorarios.controller.classroom;

import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassBlockDTO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.classroom.ClassNameDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassBlockInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassNameInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.service.classroom.ClassNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/className")
public class ClassNameController {

    @Autowired
    private ClassNameService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody ClassNameModel classNameModel) throws ClassNameInvalidException {
        boolean result = this.service.create(classNameModel);
        if(result) {
            return ResponseEntity.status(201).body(new ClassNameDTO(classNameModel));
        }
        return ResponseEntity.status(400).body("Inválido");

    }

    @GetMapping
    public ResponseEntity <List<ClassNameDTO>> read(){
        List<ClassNameModel> result = this.service.read();
        return ResponseEntity.status(200).body(ClassNameDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ClassNameDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ClassNameModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ClassNameDTO(result));
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
