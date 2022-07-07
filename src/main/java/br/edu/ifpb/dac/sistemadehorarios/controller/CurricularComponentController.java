package br.edu.ifpb.dac.sistemadehorarios.controller;

import br.edu.ifpb.dac.sistemadehorarios.DRO.CurricularComponentDRO;
import br.edu.ifpb.dac.sistemadehorarios.DTO.CurricularComponentDTO;
import br.edu.ifpb.dac.sistemadehorarios.exception.CurricularComponentInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.CurricularComponentModel;
import br.edu.ifpb.dac.sistemadehorarios.service.CurricularComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curricularComponent")
public class CurricularComponentController {
    @Autowired
    private CurricularComponentService service;

    @PostMapping()
    public ResponseEntity<CurricularComponentDTO> create(@RequestBody CurricularComponentDRO DRO) throws CurricularComponentInvalidException {
        CurricularComponentModel result = this.service.create(DRO);
        if(result!=null) {
            return ResponseEntity.status(201).body(new CurricularComponentDTO(result));
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping
    public ResponseEntity <List<CurricularComponentDTO>> read(){
        List<CurricularComponentModel> result = this.service.read();
        return ResponseEntity.status(200).body(CurricularComponentDTO.convert(result));
    }

    @GetMapping("/get-by-uuid/{uuid}")
    public ResponseEntity<CurricularComponentDTO> findByUuid(@PathVariable("uuid") String uuid) {
        CurricularComponentModel result = this.service.findByUuid(uuid);
        if(result !=  null){
            return  ResponseEntity.status(200).body(new CurricularComponentDTO(result));
        }
        return ResponseEntity.status(404).body(null);
    }


    @PutMapping("/{uuid}")
    public ResponseEntity<CurricularComponentDTO> update(@RequestBody CurricularComponentModel curricularComponentModel, @PathVariable("uuid") String uuid){
        boolean result = this.service.update(curricularComponentModel, uuid);
        if (result) {
            return ResponseEntity.status(200).body(new CurricularComponentDTO(curricularComponentModel));
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
