package br.edu.ifpb.dac.sistemadehorarios.entity.Turma;

import br.edu.ifpb.dac.sistemadehorarios.DTO.TurmaDTO;
import br.edu.ifpb.dac.sistemadehorarios.entity.Course.CourseService;
import br.edu.ifpb.dac.sistemadehorarios.exception.TurmaInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {
    @Autowired
    private TurmaService service;
    @Autowired
    private CourseService courseService;

    @PostMapping()
    public ResponseEntity<TurmaDTO> create(@RequestBody TurmaDRO model) throws TurmaInvalidException {

        TurmaModel turmaModel = new TurmaModel();

        turmaModel.setName(model.getName());
        turmaModel.setCourseModel(courseService.findByUuid(model.getCourse_uuid()));
        TurmaModel result = this.service.create(turmaModel);

        if(result!=null) {
            return ResponseEntity.status(201).body(new TurmaDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity <List<TurmaDTO>> read(){
        List<TurmaModel> result = this.service.read();
        return ResponseEntity.status(200).body(TurmaDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<TurmaDTO> findByUuid(@PathVariable("uuid") String uuid) {
        TurmaModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new TurmaDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<TurmaDTO> update(@RequestBody TurmaModel turmaModel, @PathVariable("uuid") String uuid){
        boolean result = this.service.update(turmaModel, uuid);
        if (result) {
            return ResponseEntity.status(200).body(new TurmaDTO(this.service.findByUuid(uuid)));
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
