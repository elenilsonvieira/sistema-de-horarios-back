package br.edu.ifpb.dac.sistemadehorarios.entity.Restriction;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Professor.ProfessorModel;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestrictionService extends ServiceTemplate {

    @Autowired
    private RestrictionRepository repository;

    public RestrictionModel create(RestrictionModel restriction) throws Exception {
        try {
            if (super.create(restriction, repository)) {
                return restriction;
            }
            return null;
        } catch (Exception e) {
            throw new Exception("Houve um problema ao criar a restricao. Error: "+ e.getMessage());
        }
    }

    public List<RestrictionModel> read() {
        return (List<RestrictionModel>) super.read(repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public RestrictionModel findByUuid(String uuid) {
        return (RestrictionModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(RestrictionModel restrictionModel, String uuid) {
        try {
            RestrictionModel result = this.repository.findByUuid(uuid);

            ProfessorModel professorUuid = restrictionModel.getProfessorUuid()==null ? result.getProfessorUuid() : restrictionModel.getProfessorUuid();
            WeekDayModel weekDayUuid = restrictionModel.getWeekDayUuid()==null ? result.getWeekDayUuid() : restrictionModel.getWeekDayUuid();
            ShiftModel shiftUuid = restrictionModel.getShiftUuid()==null ? result.getShiftUuid() : restrictionModel.getShiftUuid();

            result.setProfessorUuid(professorUuid);
            result.setWeekDayUuid(weekDayUuid);
            result.setShiftUuid(shiftUuid);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
    
}
