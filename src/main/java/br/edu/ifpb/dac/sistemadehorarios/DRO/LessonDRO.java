package br.edu.ifpb.dac.sistemadehorarios.DRO;


public class LessonDRO {
    private String turmaUuid;
    private String curricularComponentUuid;
    private String professorUuid;
    private String classroomUuid;
    private String calendarUuid;

    public String getTurmaUuid() {
        return turmaUuid;
    }

    public void setTurmaUuid(String turmaUuid) {
        this.turmaUuid = turmaUuid;
    }

    public String getCurricularComponentUuid() {
        return curricularComponentUuid;
    }

    public void setCurricularComponentUuid(String curricularComponentUuid) {
        this.curricularComponentUuid = curricularComponentUuid;
    }

    public String getProfessorUuid() {
        return professorUuid;
    }

    public void setProfessorUuid(String professorUuid) {
        this.professorUuid = professorUuid;
    }

    public String getClassroomUuid() {
        return classroomUuid;
    }

    public void setClassroomUuid(String classroomUuid) {
        this.classroomUuid = classroomUuid;
    }

    public String getCalendarUuid() {
        return calendarUuid;
    }

    public void setCalendarUuid(String calendarUuid) {
        this.calendarUuid = calendarUuid;
    }
}
