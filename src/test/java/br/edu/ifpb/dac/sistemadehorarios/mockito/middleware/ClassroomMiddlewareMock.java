package br.edu.ifpb.dac.sistemadehorarios.mockito.middleware;

import br.edu.ifpb.dac.sistemadehorarios.exception.ClassroomInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.middleware.ClassroomMiddleware;

import br.edu.ifpb.dac.sistemadehorarios.model.ClassroomModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomMiddlewareMock {

    @Mock
    private ClassroomMiddleware middleware = mock(ClassroomMiddleware.class);
    @Test
    public void mockInvalidResponse() {
        try {

            middleware.isValidClassroom(null);

            verify(middleware).isValidClassroom(null);
            when(middleware.isValidClassroom(null)).thenThrow(new ClassroomInvalidException("Mock test", 400));
            assertThrows(ClassroomInvalidException.class, () -> middleware.isValidClassroom(null));
        } catch (ClassroomInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mockValidResponse() {
        try {
            when(middleware.isValidClassroom(null)).thenReturn(true);
            assertTrue(middleware.isValidClassroom(null));
        } catch (ClassroomInvalidException e) {
            e.printStackTrace();
        }
    }
}
