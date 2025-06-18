package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        estado = new MuestraExperto(muestra);
    }
    
    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.VINCHUCA_GUASAYANA));
        assertEquals("Vinchuca Guasayana", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.VINCHUCA_SORDIDA));
        assertEquals("Vinchuca Sordida", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.CHINCHA_FOLIADA));
        assertEquals("Chincha Foliada", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.PHTIA_CHINCHE));
        assertEquals("Phtia Chinche", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
        assertEquals("Ninguna", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }
    
    @Test
    public void testHayEmpateEntoncesNoEstaDefinido() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
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

        estado.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
        verify(muestra).setEstado(any(MuestraVerificada.class));
        verify(muestra).cargarMuestraVerificada();
    }
}
