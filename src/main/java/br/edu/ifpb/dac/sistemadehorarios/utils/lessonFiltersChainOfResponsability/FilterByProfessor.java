package br.edu.ifpb.dac.sistemadehorarios.utils.lessonFiltersChainOfResponsability;

import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.entity.Lesson.LessonRepository;

import java.util.List;

public class FilterByProfessor extends Filter{
    private String filter;
    private LessonRepository repository;

    public FilterByProfessor(String filter, LessonRepository repository) {
        this.filter=filter;
        this.repository=repository;
    }
    @Override
    public List<LessonModel> filter(List<LessonModel> list) {
        if(!this.filter.equals("null")){
            return this.repository.getByProfessorModelFilter(this.filter);
        }
        return list;
    }
}
