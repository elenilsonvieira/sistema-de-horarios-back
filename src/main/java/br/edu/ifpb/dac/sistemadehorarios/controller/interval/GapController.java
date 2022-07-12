package br.edu.ifpb.dac.sistemadehorarios.controller.interval;

import br.edu.ifpb.dac.sistemadehorarios.DTO.interval.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.interval.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.service.interval.GapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gap")
public class GapController {

    @Autowired
    private GapService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody GapModel gapModel) throws GapInvalidException {
        boolean result = this.service.create(gapModel);
        if(result) {
            return ResponseEntity.status(201).body(new GapDTO(gapModel));
        }
        return ResponseEntity.status(400).body("Inválido");

    }

    @GetMapping
    public ResponseEntity <List<GapDTO>> read(){
        List<GapModel> result = this.service.read();
        return ResponseEntity.status(200).body(GapDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<GapDTO> findByUuid(@PathVariable("uuid") String uuid) {
        GapModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new GapDTO(result));
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
