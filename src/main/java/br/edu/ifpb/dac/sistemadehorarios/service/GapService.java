package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
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
            if(!this.isValidGap(gap)){
                return false;
            }
            this.repository.save(gap);
            return true;
        }catch (Exception error){
            System.out.println(error);
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
            if(!this.isValidGap(gap)){
                return false;
            }
            GapModel result = this.repository.findByUuid(uuid);

            int interval =gap.getInterval()==0? result.getInterval() : gap.getInterval();
            DayOfWeekEnum dayOfWeek = gap.getDayOfWeek()==null? result.getDayOfWeek() : gap.getDayOfWeek();

            result.setInterval(interval);
            result.setDayOfWeek(dayOfWeek);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public boolean delete(String uuid) {
        try {
            this.repository.deleteById(uuid);
            return true;
        }catch (Exception error){
            return false;
        }
    }

    public GapModel readByUuid(String uuid) {
        try {
            return this.repository.findByUuid(uuid);
        }catch (Exception error){
            return null;
        }
    }


    private boolean isValidGap(GapModel gap){
        GapModel isValid = this.repository.findByDayAndInterval(
                gap.getDayOfWeek().name(),
                gap.getInterval());

        if(isValid != null){
            return false;
        }
        return true;
    }

}
