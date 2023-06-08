package br.edu.ifpb.dac.sistemadehorarios.entity.Course;

public class CourseJson {
    private String id;
    private String descricao;

    public CourseJson(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public CourseModel converteCourseJsonToCourseModel() {
        CourseModel courseModel = new CourseModel();
        courseModel.setUuid(id);
        courseModel.setName(descricao);
        return  courseModel;
    }

}
