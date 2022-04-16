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
	public ResponseEntity<GapDTO> create(@RequestBody GapDTO gap){
		GapModel gapModel = new GapModel(gap.getStart(), gap.getDayOfWeek());
		boolean result = this.service.create(gapModel);
		if(result) {
			return ResponseEntity.status(201).body(gap);
		}
		return ResponseEntity.status(400).body(null);
	}
	
	@GetMapping
	public ResponseEntity <List<GapDTO>> read(){
		List<GapModel> result = this.service.read();
		return ResponseEntity.ok().body(GapDTO.convert(result));
	}
	
	@GetMapping("/get-by-uuid/{uuid}")
	
	
	@PutMapping("/{uuid}")
	public ResponseEntity<GapDTO> update(@RequestBody GapDTO newGap, @PathVariable("uuid") String uuid){
		GapModel gap = new GapModel(newGap.getStart(), newGap.getDayOfWeek());
		var result = this.service.update(gap, uuid);
		if (result) {
			return ResponseEntity.ok().body(newGap);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<String> delete(@PathVariable("uuid") String uuid){
		try {
			this.service.delete(uuid);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
