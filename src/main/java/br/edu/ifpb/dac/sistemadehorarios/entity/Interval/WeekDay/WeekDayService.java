package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftEnum;
import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Shift.ShiftModel;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.ShiftException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.WeekDayException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeekDayService extends ServiceTemplate {
    @Autowired
    private WeekDayRepository repository;

    public void createDefaultValues() throws WeekDayException {
        for (WeekDayEnum weekDayEnum: WeekDayEnum.values()) {
            WeekDayModel weekDayModel = this.findByWeekDay(weekDayEnum.getName());
            if(weekDayModel == null){
                weekDayModel = new WeekDayModel();
                weekDayModel.setWeekDay(weekDayEnum.getName());
                this.create(weekDayModel);
            }
        }
    }
    public boolean create(WeekDayModel weekDayModel) throws WeekDayException {
        try{
            return super.create(weekDayModel, this.repository);
        }catch (Exception error){
            throw new WeekDayException("Houve um problema para criar um Gap. Erro: "+error.getMessage());
        }
    }

    public List<WeekDayModel> read() {
        return (List<WeekDayModel>) super.read(this.repository);
    }
    public boolean delete(String uuid) {
        return super.delete(uuid, this.repository);
    }

    public WeekDayModel findByUuid(String uuid) {
        return (WeekDayModel) super.findByUuid(uuid, this.repository);
    }

    public WeekDayModel findByWeekDay(String weekDay){
        return this.repository.findByWeekDay(weekDay);
    }
}
