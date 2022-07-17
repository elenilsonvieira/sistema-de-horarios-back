package br.edu.ifpb.dac.sistemadehorarios.utils.lessonFiltersChainOfResponsability;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassName.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;

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
                ClassNameModel classNameModel = classroomModel.getClassNameModel();
                if(classNameModel.getName().equals(this.filter)){
                    listLesson.add(lesson);
                }
            }
            return listLesson;
        }
        return list;
    }
}
