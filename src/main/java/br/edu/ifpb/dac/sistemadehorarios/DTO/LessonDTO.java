package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class LessonDTO {
    private String uuid;
    private CorricularComponentModel corricularComponentModel;
    private ProfessorModel professorModel;
    private GapModel gapModel;
    private ClassModel classModel;

    public LessonDTO(LessonModel lessonModel){
        this.uuid = lessonModel.getUuid();
        this.corricularComponentModel = lessonModel.getCorricularComponentModel();
        this.professorModel = lessonModel.getProfessorModel();
        this.gapModel = lessonModel.getGapModel();
        this.classModel = lessonModel.getClassModel();
    }

    public static List<LessonDTO> convert(List<LessonModel> gaps){
        return gaps.stream().map(LessonDTO::new).collect(Collectors.toList());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CorricularComponentModel getCorricularComponentModel() {
        return corricularComponentModel;
    }

    public void setCorricularComponentModel(CorricularComponentModel corricularComponentModel) {
        this.corricularComponentModel = corricularComponentModel;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public void setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
    }

    public GapModel getGapModel() {
        return gapModel;
    }

    public void setGapModel(GapModel gapModel) {
        this.gapModel = gapModel;
    }

    public ClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(ClassModel classModel) {
        this.classModel = classModel;
    }
}
