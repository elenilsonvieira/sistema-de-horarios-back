package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.GapRepository;

@Service
public class GapService {
	
	@Autowired
	private GapRepository repository;
	
	public boolean create(GapModel gap) {
        try {
            this.repository.save(gap);
            return true;
        }catch (Exception error){
            return false;
        }
    }
	
	public List<GapModel> read() {
        try {
            return this.repository.findAll();
        }catch (Exception error){
            return null;
        }
    }
	
	public boolean update(GapModel gap, String uuid) {
        try {
            GapModel result = this.repository.findByUuid(uuid);
            result.setStart(gap.getStart());
            result.setDayOfWeek(gap.getDayOfWeek());
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public void delete(String uuid) {
    	repository.deleteById(uuid);
    }

    public GapModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }
}
