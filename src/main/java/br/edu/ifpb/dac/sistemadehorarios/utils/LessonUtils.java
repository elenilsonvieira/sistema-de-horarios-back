package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.DRO.LessonDRO;
import br.edu.ifpb.dac.sistemadehorarios.model.LessonModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.LessonRepository;

public class LessonUtils {

    public static boolean lessonEqualsValidation(LessonDRO lesson, LessonRepository repository){

        try{
            LessonModel result = repository.lessonEqualsValidation(
                    lesson.getClassroomUuid(),
                    lesson.getProfessorUuid(),
                    lesson.getCurricularComponentUuid(),
                    lesson.getTurmaUuid(),
                    lesson.getCourseUuid());

            return result == null;
        }catch (Exception error){
            System.out.println(error);
            return false;
        }

    }
}
