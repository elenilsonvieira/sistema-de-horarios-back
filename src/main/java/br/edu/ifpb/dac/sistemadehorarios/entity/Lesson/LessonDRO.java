package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDRO {
    private String turmaUuid;
    private String curricularComponentUuid;
    private String professorUuid;
    private String classroomUuid;
    private String calendarUuid;
    private String courseUuid;
}
