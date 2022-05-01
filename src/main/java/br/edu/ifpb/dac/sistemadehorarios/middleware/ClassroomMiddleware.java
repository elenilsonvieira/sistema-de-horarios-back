package br.edu.ifpb.dac.sistemadehorarios.middleware;

import br.edu.ifpb.dac.sistemadehorarios.exception.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import br.edu.ifpb.dac.sistemadehorarios.repository.ClassroomRepository;
import br.edu.ifpb.dac.sistemadehorarios.utils.ClassroomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClassroomMiddleware {
    @Autowired
    private ClassroomRepository repository;

    public void isValidClassroom(ClassroomModel classroom) throws ClassroomInvalidException {
        boolean result = ClassroomUtils.isValidClassroom(classroom, this.repository);
        if(!result){
            throw new ClassroomInvalidException("Invalid classroom");
        }
    }
}
