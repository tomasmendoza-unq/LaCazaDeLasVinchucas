package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class MuestraExpertoTest {
    private IMuestra muestra;
    private IUsuario owner;


    @BeforeEach
    public void setUp() {
        owner = mock(IUsuario.class);
        muestra = new MuestraExperto(owner, "Foto", "Calle 123");
    }
    
    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        muestra.agregarOpinionExperto(Opinion.VINCHUCA_GUASAYANA);
        assertEquals("Vinchuca Guasayana", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        muestra.agregarOpinionExperto(Opinion.VINCHUCA_INFESTANS);
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        muestra.agregarOpinionExperto(Opinion.VINCHUCA_SORDIDA);
        assertEquals("Vinchuca Sordida", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        muestra.agregarOpinionExperto(Opinion.CHINCHA_FOLIADA);
        assertEquals("Chincha Foliada", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        muestra.agregarOpinionExperto(Opinion.PHTIA_CHINCHE);
        assertEquals("Phtia Chinche", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        muestra.agregarOpinionExperto(Opinion.NINGUNA);
        assertEquals("Ninguna", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        muestra.agregarOpinionExperto(Opinion.IMAGEN_POCO_CLARA);
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }
    
    @Test
    public void testHayEmpateEntoncesNoEstaDefinido() {
        
        muestra.agregarOpinionExperto(Opinion.IMAGEN_POCO_CLARA);
        muestra.agregarOpinionExperto(Opinion.NINGUNA);
        assertEquals("No definido", muestra.resultadoActual());
    }

    @Test
    public void testAgregarOpinionBasicoLanzaExcepcion() {

        Executable accion = () -> muestra.agregarOpinionBasico(mock(Opinion.class));
        SinAccesoAMuestraException exception = assertThrows(SinAccesoAMuestraException.class, accion);

        assertEquals("Un usuario básico no puede opinar en una muestra con opiniones de expertos", exception.getMessage());
    }

    @Test
    public void testAgrearDosOpinionesExpertasIgualesVerificaLaMuestra() {
        muestra.agregarOpinionExperto(Opinion.NINGUNA);
        muestra.agregarOpinionExperto(Opinion.NINGUNA);
        verify(owner).setMuestraPublicada(any(MuestraVerificada.class));
    }
}
