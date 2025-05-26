package unq.integrador;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MuestraLibreTest {
    private IMuestra muestra;
    private IUsuario owner;
    private IUsuario user;
    private Opinion op;

    @BeforeEach
    public void setUp() {
        owner   = mock(IUsuario.class);
        user    = mock(IUsuario.class);
        op      = mock(Opinion.class);
        // muestra = new MuestraLibre(user, "Foto", "Calle 123");
        muestra = new MuestraLibre(owner, "Foto", "Calle 123");
    }

    @Test
    public void testResultadoActualSinOpiniones() {
        when(this.user.opinarDeMuestra(muestra, op)).thenAnswer(CALLS_REAL_METHODS);
        
        verify(muestra).agregarOpinion(op, false);
    }

}
