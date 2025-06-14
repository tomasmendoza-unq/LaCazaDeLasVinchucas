package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MuestraTest {
    private IMuestra muestra;
    private IUsuario user;

    @BeforeEach
    public void setUp() {
        user = mock(IUsuario.class);
        muestra = new MuestraLibre(user, "Foto", "Calle 123");
    }

    @Test
    public void testGetters() {
        when(user.getID()).thenReturn(1);
        
        assertEquals(1, muestra.getIDUsuario());
        assertEquals("Foto", muestra.getFotografia());
        assertEquals("Calle 123", muestra.getUbicacion());

    }
}
