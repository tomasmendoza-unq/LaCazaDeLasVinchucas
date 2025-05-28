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

    @Test
    public void testResultadoActualConEmpateAmpliado() {
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        muestra.agregarOpinionBasico(Opinion.NINGUNA);
        muestra.agregarOpinionBasico(Opinion.NINGUNA);
        assertEquals("No definido", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_GUASAYANA);
        assertEquals("Vinchuca Guasayana", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_INFESTANS);
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        muestra.agregarOpinionBasico(Opinion.VINCHUCA_SORDIDA);
        assertEquals("Vinchuca Sordida", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        muestra.agregarOpinionBasico(Opinion.CHINCHA_FOLIADA);
        assertEquals("Chincha Foliada", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        muestra.agregarOpinionBasico(Opinion.PHTIA_CHINCHE);
        assertEquals("Phtia Chinche", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        muestra.agregarOpinionBasico(Opinion.NINGUNA);
        assertEquals("Ninguna", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        muestra.agregarOpinionBasico(Opinion.IMAGEN_POCO_CLARA);
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }

    @Test
    public void testAgregarOpinionExpertoEntoncesCambiaElTipoDeMuestra() {
        muestra.agregarOpinionExperto(Opinion.NINGUNA);
        verify(owner).setMuestraPublicada(any(MuestraExperto.class));
    }
}
