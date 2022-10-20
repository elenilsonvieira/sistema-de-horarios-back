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

            ProfessorModel professorUuid = restrictionModel.getProfessorModel()==null ? result.getProfessorModel() : restrictionModel.getProfessorModel();
            WeekDayModel weekDayUuid = restrictionModel.getWeekDayModel()==null ? result.getWeekDayModel() : restrictionModel.getWeekDayModel();
            ShiftModel shiftUuid = restrictionModel.getShiftModel()==null ? result.getShiftModel() : restrictionModel.getShiftModel();

            result.setProfessorModel(professorUuid);
            result.setWeekDayModel(weekDayUuid);
            result.setShiftModel(shiftUuid);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }
    
}
