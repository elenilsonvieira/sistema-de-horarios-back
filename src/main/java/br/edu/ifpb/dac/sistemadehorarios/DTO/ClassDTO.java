package br.edu.ifpb.dac.sistemadehorarios.DTO;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassModel;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.model.CourseModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClassDTO {

    private String uuid;
    private String name;
    private CourseModel course;


    public ClassDTO(ClassModel classModel) {
        this.course = classModel.getCourseModel();
        this.name = classModel.getName();
        this.uuid = classModel.getUuid();
    }

    public static List<ClassDTO> convert(List<ClassModel> classModel){
        return classModel.stream().map(ClassDTO::new).collect(Collectors.toList());
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

    public CourseModel getCourse() {
        return course;
    }

    public void setCourse(CourseModel course) {
        this.course = course;
    }
}
