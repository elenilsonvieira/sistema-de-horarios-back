package br.edu.ifpb.dac.sistemadehorarios.utils;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassroomRepository;

public class ClassroomUtils {

    public static boolean isValidClassroom(ClassroomModel classroom, ClassroomRepository repository){

        ClassroomModel result = repository.classroomEqualsValidation(
                classroom.getName(),
                classroom.getBlock());
        return result == null;
    }
}
