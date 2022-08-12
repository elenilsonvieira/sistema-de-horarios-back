package br.edu.ifpb.dac.sistemadehorarios.unity;

import br.edu.ifpb.dac.sistemadehorarios.exception.*;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassBlockInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassNameInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.classroom.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.GapException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.ShiftException;
import br.edu.ifpb.dac.sistemadehorarios.exception.interval.WeekDayException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUnityTest {

    @Test
    public void validException(){
        assertThrows(ErrorProject.class, () ->{
            throw new UserInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new CalendarInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new CourseInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new CurricularComponentInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new LessonInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new ProfessorInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new TurmaInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new UserInvalidException("test", 400);
        });
        assertThrows(Exception.class, () ->{
            throw new GapException("test");
        });
        assertThrows(Exception.class, () ->{
            throw new ShiftException("test");
        });
        assertThrows(Exception.class, () ->{
            throw new WeekDayException("test");
        });
        assertThrows(ErrorProject.class, () ->{
            throw new IntervalInvalidException("test", 400);
        });

        assertThrows(ErrorProject.class, () ->{
            throw new ClassBlockInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new ClassNameInvalidException("test", 400);
        });
        assertThrows(ErrorProject.class, () ->{
            throw new ClassroomInvalidException("test", 400);
        });
    }

    @Test
    public void exceptionNullMessage(){
        assertThrows(ErrorProject.class, () ->{
            throw new ClassroomInvalidException(null, 0);
        });
    }

}
