package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.LessonRepository;

public class LessonUtils {

    public static boolean lessonEqualsValidation(LessonDRO lesson, LessonRepository repository){

        try{
            LessonModel result = repository.lessonEqualsValidation(
                    lesson.getClassroomUuid(),
                    lesson.getProfessorUuid(),
                    lesson.getIntervalUuid(),
                    lesson.getCorricularComponentlUuid(),
                    lesson.getTurmaUuid());

            return result == null;
        }catch (Exception error){
            return false;
        }

    }

    public static boolean classroomAndIntervalValidation(LessonDRO lesson, LessonRepository repository){

        try{
            LessonModel result = repository.classroomAndIntervalValidation(
                    lesson.getClassroomUuid(),
                    lesson.getIntervalUuid());

            return result == null;
        }catch (Exception error){
            return false;
        }

    }
}
