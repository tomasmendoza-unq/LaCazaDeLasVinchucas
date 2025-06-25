package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import unq.integrador.IEstadoDeMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.MuestraExperto;
import unq.integrador.impls.MuestraVerificada;
import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;

public class MuestraExpertoTest {
    private IEstadoDeMuestra estado;
    private IMuestra muestra;
    private Opinion op1;
    private Opinion op2;

    @BeforeEach
    public void setUp() {
        op1 = mock(Opinion.class);
        op2 = mock(Opinion.class);
        muestra = mock(IMuestra.class);
        estado = new MuestraExperto(muestra);
    }

    @Test
    public void testNoEsMuestraVerificada() {
        assertEquals(false, estado.esVerificada());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Vinchuca Guasayana", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaSordida() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Vinchuca Sordida", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsChinchaFoliada() {
        when(op1.getTipo()).thenReturn(TipoOpinion.CHINCHA_FOLIADA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Chincha Foliada", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsPhtiaChinche() {
        when(op1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Phtia Chinche", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Ninguna", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        when(op1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }
    @Test
    public void testHayEmpateEntoncesNoEstaDefinido() {
        when(op1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op2.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op2));
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testAgregarOpinionBasicoLanzaExcepcion() {

        Executable accion = () -> estado.agregarOpinionBasico(mock(Opinion.class));
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);

        assertEquals("Un usuario básico no puede opinar en una muestra con opiniones de expertos", exception.getMessage());
    }

    @Test
    public void testAgrearDosOpinionesExpertasIgualesVerificaLaestado() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        when(op2.getTipo()).thenReturn(TipoOpinion.NINGUNA);
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op1));
         assertDoesNotThrow(() -> estado.agregarOpinionExperto(op2));
        verify(muestra).setEstado(any(MuestraVerificada.class));
        verify(muestra).cargarMuestraVerificada();
    }
}
