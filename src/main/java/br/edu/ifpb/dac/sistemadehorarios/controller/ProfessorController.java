package br.edu.ifpb.dac.sistemadehorarios.controller;

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
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO professor){

        ProfessorModel professorModel = new ProfessorModel(professor.getName(), professor.getArea());
        boolean result = this.professorService.create(professorModel);
        if(result) {
            return ResponseEntity.status(201).body(professor);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> read(){
        List<ProfessorModel> result = this.professorService.read();
        return ResponseEntity.ok().body(ProfessorDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<ProfessorDTO> readByUuid(@PathVariable("uuid") String uuid) {
        ProfessorModel result = this.professorService.readByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.ok().body(new ProfessorDTO(result));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ProfessorDTO> update(@RequestBody ProfessorDTO professor, @PathVariable("uuid") String uuid) {
        ProfessorModel professorModel = new ProfessorModel(professor.getName(),professor.getArea());
        var result = this.professorService.update(professorModel, uuid);
        if(result){
            return  ResponseEntity.ok().body(professor);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ProfessorDTO> delete(@PathVariable("uuid") String uuid){
        ProfessorModel result = this.professorService.delete(uuid);
        if(result != null){
            return  ResponseEntity.ok().body(new ProfessorDTO(result));
        }
        return ResponseEntity.badRequest().body(null);
    }


}
