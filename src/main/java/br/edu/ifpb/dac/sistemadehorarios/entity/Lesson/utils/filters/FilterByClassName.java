package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;

import java.util.ArrayList;
import java.util.List;

public class FilterByClassName extends Filter {
    private String filter;

    public FilterByClassName(String filter) {
        this.filter=filter;
    }
    @Override
    public List<LessonModel> filter( List<LessonModel> list) {
        if(!this.filter.equals("null")){
            List<LessonModel> listLesson = new ArrayList<LessonModel>();
            for (LessonModel lesson: list) {
                ClassroomModel classroomModel = lesson.getClassroomModel();
                if(classroomModel.getName().equals(this.filter)){
                    listLesson.add(lesson);
                }
            }
            return listLesson;
        }
        return list;
    }
}
