package br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.utils.filters;

import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.ClassBlock.ClassBlockModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Classroom.Classroom.ClassroomModel;

import java.util.ArrayList;
import java.util.List;

public class FilterByBlock extends Filter {

    private String filter;

    public FilterByBlock(String filter) {
        this.filter=filter;
    }

    @Override
    public List<LessonModel> filter(List<LessonModel> list) {

        if(!this.filter.equals("null")){
            List<LessonModel> listLesson = new ArrayList<LessonModel>();
            for (LessonModel lesson: list) {
                ClassroomModel classroomModel = lesson.getClassroomModel();
                ClassBlockModel classBlockModel = classroomModel.getClassBlockModel();
                if(classBlockModel.getBlockName().equals(this.filter)){
                    listLesson.add(lesson);
                }
            }
            return listLesson;
        }
        return list;
    }
}
