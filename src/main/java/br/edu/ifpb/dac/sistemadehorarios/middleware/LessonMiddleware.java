package br.edu.ifpb.dac.sistemadehorarios.middleware;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.repository.LessonRepository;
import br.edu.ifpb.dac.sistemadehorarios.utils.LessonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LessonMiddleware {

    @Autowired
    private LessonRepository repository;

    public boolean lessonEqualsValidation(LessonDRO lesson) throws LessonInvalidException {
        boolean result = LessonUtils.lessonEqualsValidation(lesson, this.repository);
        this.throwError(result);
        return true;
    }

    private void throwError(boolean result) throws LessonInvalidException {
        if(!result){
            throw new LessonInvalidException("Lesson is duplicate", 400);
        }
    }

}