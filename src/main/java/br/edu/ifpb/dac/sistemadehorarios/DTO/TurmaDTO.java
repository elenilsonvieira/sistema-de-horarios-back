package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.TurmaModel;

import java.util.List;
import java.util.stream.Collectors;

public class TurmaDTO {

    private String uuid;
    private String name;
    private CourseDTO course;


    public TurmaDTO(TurmaModel turmaModel) {
        this.name = turmaModel.getName();
        this.uuid = turmaModel.getUuid();
        this.course = new CourseDTO(turmaModel.getCourse());
    }

    public static List<TurmaDTO> convert(List<TurmaModel> turmaModel){
        return turmaModel.stream().map(TurmaDTO::new).collect(Collectors.toList());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseDTO getCourse() {
        return course;
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }
}
