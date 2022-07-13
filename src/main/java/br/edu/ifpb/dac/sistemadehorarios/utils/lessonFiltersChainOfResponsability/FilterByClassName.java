package br.edu.ifpb.dac.sistemadehorarios.utils.lessonFiltersChainOfResponsability;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassNameModel;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassroomModel;

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
