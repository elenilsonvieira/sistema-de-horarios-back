package br.edu.ifpb.dac.sistemadehorarios.entity.Interval.Interval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervalDRO {

    private String lessonUuid;
    private String gapUuid;
    private String shiftUuid;
    private String weekDayUuid;
}
