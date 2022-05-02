package br.edu.ifpb.dac.sistemadehorarios.mockito;

import br.edu.ifpb.dac.sistemadehorarios.exception.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.exception.LessonInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.middleware.LessonMiddleware;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LessonMiddlewareMock {

    @Mock
    private LessonMiddleware middleware = mock(LessonMiddleware.class);

    @Test
    public void mockInvalidResponse() {
        try {

            middleware.lessonEqualsValidation(null);

            verify(middleware).lessonEqualsValidation(null);
            when(middleware.lessonEqualsValidation(null)).thenThrow(new LessonInvalidException("Mock test"));
            assertThrows(LessonInvalidException.class, () -> middleware.lessonEqualsValidation(null));


            middleware.classroomAndIntervalValidation(null);
            when(middleware.classroomAndIntervalValidation(null)).thenThrow(new LessonInvalidException("Mock test"));
            assertThrows(LessonInvalidException.class, () -> middleware.classroomAndIntervalValidation(null));

        } catch (LessonInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mockValidResponse() {
        try {
            when(middleware.lessonEqualsValidation(null)).thenReturn(true);
            assertTrue(middleware.lessonEqualsValidation(null));

            when(middleware.classroomAndIntervalValidation(null)).thenReturn(true);
            assertTrue(middleware.classroomAndIntervalValidation(null));
        } catch (LessonInvalidException e) {
            e.printStackTrace();
        }
    }
}
