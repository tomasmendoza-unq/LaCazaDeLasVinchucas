package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IEstadoDeMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.Muestras.MuestraExperto;
import unq.integrador.impls.Muestras.MuestraLibre;
import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;

public class MuestraLibreTest {
    private IEstadoDeMuestra estado;
    private IMuestra muestra;
    private Opinion op1;
    private Opinion op2;
    private Opinion op3;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        op1 = mock(Opinion.class);
        op3 = mock(Opinion.class);
        op2 = mock(Opinion.class);
        estado = new MuestraLibre(muestra);
    }

    @Test
    public void testNoEsMuestraVerificada() {
        assertEquals(false, estado.esVerificada());
    }

    @Test
    public void testResultadoActualSinOpiniones() {
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConDistintasOpiniones() {
        // Se agrega 1 opinión
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());

        // Se agregan 2 opinión, pero el resultado actual sigue siendo la chincha
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op2));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());

        // Se agregan 2 opinión, pero el resultado más actual cambia por imagen poco clara
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op2));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op2));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpate() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op2));
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpateAmpliado() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op3.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op2));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op3));
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op3));
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Vinchuca Guasayana", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaSordida() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Vinchuca Sordida", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsChinchaFoliada() {
        when(op1.getTipo()).thenReturn(TipoOpinion.CHINCHA_FOLIADA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Chincha Foliada", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsPhtiaChinche() {
        when(op1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Phtia Chinche", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Ninguna", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        when(op1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
         assertDoesNotThrow(() -> estado.agregarOpinionBasico(op1));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testAgregarOpinionExpertoEntoncesCambiaElTipoDeestado() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        verify(muestra).setEstado(any(MuestraExperto.class));
    }
}
