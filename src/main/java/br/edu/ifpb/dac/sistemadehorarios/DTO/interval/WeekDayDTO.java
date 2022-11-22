package br.edu.ifpb.dac.sistemadehorarios.DTO.interval;

import br.edu.ifpb.dac.sistemadehorarios.entity.Interval.WeekDay.WeekDayModel;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class WeekDayDTO {
    private DayOfWeek dayOfWeek;
    private String displayName;
    private String uuid;


    public WeekDayDTO(WeekDayModel weekDayModel) {
        this.dayOfWeek = weekDayModel.getDayOfWeek();
        this.displayName = weekDayModel.getDisplayName();
        this.uuid = weekDayModel.getUuid();
    }
    public static List<WeekDayDTO> convert(List<WeekDayModel> weekDayModelList){
        return weekDayModelList.stream().map(WeekDayDTO::new).collect(Collectors.toList());
    }
}
