package br.edu.ifpb.dac.sistemadehorarios.service;

import br.edu.ifpb.dac.sistemadehorarios.model.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService extends ServiceTemplate{

    @Autowired
    private CalendarRepository repository;

    public boolean create(CalendarModel calendar) {
        return super.create(calendar, this.repository);
    }

    public List<CalendarModel> read() {
        return (List<CalendarModel>) super.read(this.repository);
    }

    public boolean delete(String uuid) {
        return super.delete(uuid,this.repository);
    }

    public CalendarModel findByUuid(String uuid) {
        return (CalendarModel) super.findByUuid(uuid, this.repository);
    }

    public boolean update(CalendarModel calendar, String uuid) {
        try {
            CalendarModel result = this.repository.findByUuid(uuid);
            String semester = calendar.getSemester()==null? result.getSemester() : calendar.getSemester();
            result.setSemester(semester);
            this.repository.save(result);
            return true;
        }catch (Exception error){
            return false;
        }
    }

}
