package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDRO {
    private String turmaUuid;
    private String curricularComponentUuid;
    private String classroomUuid;
    private String calendarUuid;
    private String courseUuid;
    private String professorUuid;
    private String intervalUuid;

    @Override
    public String toString() {
        return "LessonDRO{" +
                "turmaUuid='" + turmaUuid + '\'' +
                ", curricularComponentUuid='" + curricularComponentUuid + '\'' +
                ", classroomUuid='" + classroomUuid + '\'' +
                ", calendarUuid='" + calendarUuid + '\'' +
                ", courseUuid='" + courseUuid + '\'' +
                ", professorUuid='" + professorUuid + '\'' +
                ", intervalUuid='" + intervalUuid + '\'' +
                '}';
    }
}
