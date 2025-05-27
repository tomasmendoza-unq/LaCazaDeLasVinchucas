package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MuestraLibreTest {
    private IMuestra muestra;
    private IUsuario owner;

    @BeforeEach
    public void setUp() {
        owner   = mock(IUsuario.class);
        muestra = new MuestraLibre(owner, "Foto", "Calle 123");
    }

    @Test
    public void testResultadoActualSinOpiniones() {
        assertEquals("No definido", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualConDistintasOpiniones() {
        // Se agrega 1 opinión
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado actual sigue siendo la chincha  
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado más actual cambia por imagen poco clara  
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpate() {
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        assertEquals("No definido", muestra.resultadoActual());
    }
}
