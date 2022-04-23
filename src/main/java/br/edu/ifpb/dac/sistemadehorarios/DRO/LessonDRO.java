package br.edu.ifpb.dac.sistemadehorarios.DRO;


public class LessonDRO {
    private String classModelUuid;
    private String corricularComponentModelUuid;
    private String gapModelUuid;
    private String professorModelUuid;

    public String getClassModelUuid() {
        return classModelUuid;
    }

    public void setClassModelUuid(String classModelUuid) {
        this.classModelUuid = classModelUuid;
    }

    public String getCorricularComponentModelUuid() {
        return corricularComponentModelUuid;
    }

    public void setCorricularComponentModelUuid(String corricularComponentModelUuid) {
        this.corricularComponentModelUuid = corricularComponentModelUuid;
    }

    public String getGapModelUuid() {
        return gapModelUuid;
    }

    public void setGapModelUuid(String gapModelUuid) {
        this.gapModelUuid = gapModelUuid;
    }

    public String getProfessorModelUuid() {
        return professorModelUuid;
    }

    public void setProfessorModelUuid(String professorModelUuid) {
        this.professorModelUuid = professorModelUuid;
    }
}
