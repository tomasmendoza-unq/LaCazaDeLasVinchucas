package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import unq.integrador.IEstadoDeMuestra;
import unq.integrador.impls.MuestraVerificada;
import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;

public class MuestraVerificadaTest {
    private Opinion opinion;
    private IEstadoDeMuestra estado;

    @BeforeEach
    public void setUp() {
        opinion = mock(Opinion.class);
        estado = new MuestraVerificada(TipoOpinion.NINGUNA);
    }
    
    @Test
    public void testResultadoActual() {
        assertEquals("Ninguna", estado.resultadoActual());
    }
    
    @Test
    public void testAgregarUnaOpinionBasicaLanzaExcepcion() {
        // muestra = new MuestraVerificada(owner, "Foto", ubicacion, historial, TipoOpinion.NINGUNA);
        Executable accion = () -> estado.agregarOpinionBasico(opinion);
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);
        
        assertEquals("Los usuarios no pueden opinar en una muestra verificada", exception.getMessage());
    }
    
    @Test
    public void testAgregarUnaOpinionExpertoLanzaExcepcion() {
        // muestra = new MuestraVerificada(owner, "Foto", ubicacion, historial, TipoOpinion.NINGUNA);
        Executable accion = () -> estado.agregarOpinionExperto(opinion);
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);
        
        assertEquals("Los usuarios no pueden opinar en una muestra verificada", exception.getMessage());
    }
}
