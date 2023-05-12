package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.entity.Calendar.CalendarModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CalendarDTO {

    private String uuid;
    private String semester;

    public CalendarDTO(CalendarModel model) {
        this.uuid = model.getUuid();
        this.semester = model.getSemester();
    }

    public static List<CalendarDTO> convert(List<CalendarModel> classes){
        return classes.stream().map(CalendarDTO::new).collect(Collectors.toList());
    }
}
