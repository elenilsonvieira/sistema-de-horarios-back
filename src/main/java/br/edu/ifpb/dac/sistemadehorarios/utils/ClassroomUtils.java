package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.model.classroom.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.classroom.ClassroomRepository;

public class ClassroomUtils {

    public static boolean isValidClassroom(ClassroomModel classroom, ClassroomRepository repository){

        ClassroomModel result = repository.classroomEqualsValidation(
                classroom.getClassNameModel().getUuid(),
                classroom.getClassBlockModel().getUuid());
        return result == null;
    }
}
