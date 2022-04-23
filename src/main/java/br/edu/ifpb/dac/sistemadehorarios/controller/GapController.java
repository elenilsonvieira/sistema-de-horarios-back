package br.edu.ifpb.dac.sistemadehorarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.sistemadehorarios.DTO.GapDTO;
import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.service.GapService;

@RestController
@RequestMapping("/gap")
public class GapController {
	
	@Autowired
	private GapService service;
	
	@PostMapping
	public ResponseEntity<GapDTO> create(@RequestBody GapModel gap){
		boolean result = this.service.create(gap);
		if(result) {
			return ResponseEntity.status(201).body(new GapDTO(gap));
		}
		return ResponseEntity.status(400).body(null);
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
	
	@PutMapping("/{uuid}")
	public ResponseEntity<GapDTO> update(@RequestBody GapModel gap, @PathVariable("uuid") String uuid){
		boolean result = this.service.update(gap, uuid);
		if (result) {
			return ResponseEntity.status(200).body(new GapDTO(gap));
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
