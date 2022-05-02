package br.edu.ifpb.dac.sistemadehorarios.mockito;

import br.edu.ifpb.dac.sistemadehorarios.exception.IntervalInvalidException;
import br.edu.ifpb.dac.sistemadehorarios.middleware.IntervalMiddleware;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IntervalMiddlewareMock {

    @Mock
    private IntervalMiddleware middleware = mock(IntervalMiddleware.class);
    @Test
    public void mockInvalidResponse() {
        try {

            middleware.isValidInterval(null);

            verify(middleware).isValidInterval(null);
            when(middleware.isValidInterval(null)).thenThrow(new IntervalInvalidException("Mock test"));
            assertThrows(IntervalInvalidException.class, () -> middleware.isValidInterval(null));
        }catch (IntervalInvalidException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mockValidResponse() {
        try {
            when(middleware.isValidInterval(null)).thenReturn(true);
            assertTrue(middleware.isValidInterval(null));
        } catch (IntervalInvalidException e) {
            e.printStackTrace();
        }
    }
}
