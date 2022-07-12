package br.edu.ifpb.dac.sistemadehorarios.utils.lessonChainOfResponsability;

import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;

import java.util.List;

public abstract class Filter {

    public abstract List<LessonModel> filter(List<LessonModel> list);

    public static List<LessonModel> getResult(List<Filter> listFilters, List<LessonModel> listForFilter) {

        List<LessonModel> listLesson = null;
        for (Filter filter: listFilters) {
            if(listLesson == null){
                listLesson = filter.filter(listForFilter);
            }else{
                listLesson = filter.filter(listLesson);
            }
        }
        return listLesson;
    }
}
