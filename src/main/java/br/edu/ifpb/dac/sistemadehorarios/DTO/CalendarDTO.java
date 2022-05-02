package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.CalendarModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CalendarDTO {

    private String uuid;
    private String semester;
    private Date create_at;


    public CalendarDTO(CalendarModel model) {
        this.uuid = model.getUuid();
        this.semester = model.getSemester();
        this.create_at = model.getCreate_at();
    }
    public static List<CalendarDTO> convert(List<CalendarModel> classes){
        return classes.stream().map(CalendarDTO::new).collect(Collectors.toList());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }
}
