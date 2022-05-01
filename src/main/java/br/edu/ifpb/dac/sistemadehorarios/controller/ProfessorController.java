package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.DRO.ProfessorDRO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.ProfessorDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ProfessorRepository;
import br.edu.ifpb.dac.sistemadehorarios.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDRO DRO){
        ProfessorModel result = this.professorService.create(DRO);
        if(result!=null) {
            return ResponseEntity.status(201).body(new ProfessorDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> read(){
        List<ProfessorModel> result = this.professorService.read();
        return ResponseEntity.status(200).body(ProfessorDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ProfessorDTO> findByUuid(@PathVariable("uuid") String uuid) {
        ProfessorModel result = this.professorService.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new ProfessorDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProfessorDTO> update(@RequestBody ProfessorModel professor, @PathVariable("uuid") String uuid) {
        boolean result = this.professorService.update(professor, uuid);
        if(result){
            return  ResponseEntity.status(200).body(new ProfessorDTO(professor));
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
        boolean result = this.professorService.delete(uuid);
        if(result){
            return  ResponseEntity.status(200).body("OK");
        }
        return ResponseEntity.status(404).body("NOT OK");
    }


}
