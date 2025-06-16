package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.impls.MuestraVerificada;
import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;

public class MuestraVerificadaTest {
    private IMuestra muestra;
    private IUsuario owner;
    private ArrayList<String> historial;

    @BeforeEach
    public void setUp() {
        historial = new ArrayList<String>();
        owner = mock(IUsuario.class);
    }
    
    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.VINCHUCA_GUASAYANA);
        assertEquals("Vinchuca Guasayana", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.VINCHUCA_INFESTANS);
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.VINCHUCA_SORDIDA);
        assertEquals("Vinchuca Sordida", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.CHINCHA_FOLIADA);
        assertEquals("Chincha Foliada", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.PHTIA_CHINCHE);
        assertEquals("Phtia Chinche", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsImagenPocoClara() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.IMAGEN_POCO_CLARA);
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.NINGUNA);
        assertEquals("Ninguna", muestra.resultadoActual());
    }
    
    @Test
    public void testAgregarUnaOpinionBasicaLanzaExcepcion() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.NINGUNA);
        Executable accion = () -> muestra.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);
        
        assertEquals("Los usuarios no pueden opinar en una muestra verificada", exception.getMessage());
    }
    
    @Test
    public void testAgregarUnaOpinionExpertoLanzaExcepcion() {
        muestra = new MuestraVerificada(owner, "Foto", "Calle 123", historial, TipoOpinion.NINGUNA);
        Executable accion = () -> muestra.agregarOpinionExperto(mock(Opinion.class));
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);
        
        assertEquals("Los usuarios no pueden opinar en una muestra verificada", exception.getMessage());
    }
}
