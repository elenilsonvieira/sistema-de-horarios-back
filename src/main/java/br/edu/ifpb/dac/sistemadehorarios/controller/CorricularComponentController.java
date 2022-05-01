package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CorricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.CorricularComponentDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.CorricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.service.CorricularComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corricularComponent")
public class CorricularComponentController {
    @Autowired
    private CorricularComponentService service;

    @PostMapping()
    public ResponseEntity<CorricularComponentDTO> create(@RequestBody CorricularComponentDRO DRO){
        CorricularComponentModel result = this.service.create(DRO);
        if(result!=null) {
            return ResponseEntity.status(201).body(new CorricularComponentDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity <List<CorricularComponentDTO>> read(){
        List<CorricularComponentModel> result = this.service.read();
        return ResponseEntity.status(200).body(CorricularComponentDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<CorricularComponentDTO> findByUuid(@PathVariable("uuid") String uuid) {
        CorricularComponentModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new CorricularComponentDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<CorricularComponentDTO> update(@RequestBody CorricularComponentModel corricularComponentModel, @PathVariable("uuid") String uuid){
        boolean result = this.service.update(corricularComponentModel, uuid);
        if (result) {
            return ResponseEntity.status(200).body(new CorricularComponentDTO(corricularComponentModel));
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
