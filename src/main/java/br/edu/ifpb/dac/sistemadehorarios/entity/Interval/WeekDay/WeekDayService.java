package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay;

import br.edu.ifpb.dac.sistemadehorarios.exception.interval.WeekDayException;
import br.edu.ifpb.dac.sistemadehorarios.template.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class WeekDayService extends ServiceTemplate {
    
    @Autowired
    private WeekDayRepository repository;

    public void createDefaultValues() throws WeekDayException {
        for (DayOfWeek day : DayOfWeek.values()) {
            WeekDayModel weekDayModel = this.findByWeekDay(day);

            if(weekDayModel == null){
                weekDayModel = new WeekDayModel();
                weekDayModel.setDayOfWeek(day);
                weekDayModel.setDisplayName(day.getDisplayName(
                    TextStyle.FULL, Locale.forLanguageTag("pt")));

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

    public WeekDayModel findByWeekDay(DayOfWeek weekDay){
        return this.repository.findByDayOfWeek(weekDay);
    }

    public WeekDayModel findByDisplayName(String displayName){
        return this.repository.findByDisplayName(displayName);
    }
}
