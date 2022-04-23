package br.edu.ifpb.dac.sistemadehorarios.service;

import java.util.List;

import br.edu.ifpb.dac.sistemadehorarios.ENUM.DayOfWeekEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.sistemadehorarios.model.GapModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.GapRepository;

@Service
public class GapService extends ServiceAbstract{
	
	@Autowired
	private GapRepository repository;
	
	public boolean create(GapModel gap) {
        if(!this.isValidGap(gap)){
            return false;
        }
        return super.create(gap, this.repository);
    }
	
	public List<GapModel> read() {
        return (List<GapModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public GapModel findByUuid(String uuid) {
        return (GapModel) super.findByUuid(uuid, this.repository);
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

    private boolean isValidGap(GapModel gap){
        GapModel isValid = this.repository.findByDayAndInterval(
                gap.getDayOfWeek().name(),
                gap.getShift().name(),
                gap.getInterval());

        if(isValid != null){
            return false;
        }
        return true;
    }

}
