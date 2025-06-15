package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.Enums.TipoOpinion;
import unq.integrador.Impls.MuestraExperto;
import unq.integrador.Impls.MuestraLibre;
import unq.integrador.Impls.Opinion;

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

        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado actual sigue siendo la chincha  
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado más actual cambia por imagen poco clara  
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpate() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("No definido", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpateAmpliado() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        assertEquals("No definido", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_GUASAYANA));
        assertEquals("Vinchuca Guasayana", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_INFESTANS));
        assertEquals("Vinchuca Infestans", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.VINCHUCA_SORDIDA));
        assertEquals("Vinchuca Sordida", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.CHINCHA_FOLIADA));
        assertEquals("Chincha Foliada", muestra.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.PHTIA_CHINCHE));
        assertEquals("Phtia Chinche", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.NINGUNA));
        assertEquals("Ninguna", muestra.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        muestra.agregarOpinionBasico(new Opinion(TipoOpinion.IMAGEN_POCO_CLARA));
        assertEquals("Imagen poco clara", muestra.resultadoActual());
    }

    @Test
    public void testAgregarOpinionExpertoEntoncesCambiaElTipoDeMuestra() {
        muestra.agregarOpinionExperto(new Opinion(TipoOpinion.NINGUNA));
        verify(owner).setMuestraPublicada(any(MuestraExperto.class));
    }
}
