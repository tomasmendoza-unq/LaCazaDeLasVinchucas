package unq.integrador.testsMuestras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IEstadoDeMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.MuestraExperto;
import unq.integrador.impls.MuestraLibre;
import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;

public class MuestraLibreTest {
    private IEstadoDeMuestra estado;
    private IMuestra muestra;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        estado = new MuestraLibre(muestra);
    }

    @Test
    public void testResultadoActualSinOpiniones() {
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConDistintasOpiniones() {
        // Se agrega 1 opinión

        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado actual sigue siendo la chincha  
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado más actual cambia por imagen poco clara  
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpate() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpateAmpliado() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_GUASAYANA));
        assertEquals("Vinchuca Guasayana", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_SORDIDA));
        assertEquals("Vinchuca Sordida", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.CHINCHA_FOLIADA));
        assertEquals("Chincha Foliada", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.PHTIA_CHINCHE));
        assertEquals("Phtia Chinche", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        assertEquals("Ninguna", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        estado.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testAgregarOpinionExpertoEntoncesCambiaElTipoDeestado() {
        estado.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
        verify(muestra).setEstado(any(MuestraExperto.class));
    }
}
