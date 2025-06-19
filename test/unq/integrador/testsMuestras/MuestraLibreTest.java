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
    public void testResultadoActualSinOpiniones() {
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConDistintasOpiniones() {
        // Se agrega 1 opinión
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

        estado.agregarOpinionBasico(op1);
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado actual sigue siendo la chincha  
        estado.agregarOpinionBasico(op1);
        estado.agregarOpinionBasico(op2);
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
        
        // Se agregan 2 opinión, pero el resultado más actual cambia por imagen poco clara  
        estado.agregarOpinionBasico(op2);
        estado.agregarOpinionBasico(op2);
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpate() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);

        estado.agregarOpinionBasico(op1);
        estado.agregarOpinionBasico(op2);
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualConEmpateAmpliado() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        when(op2.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        when(op3.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        estado.agregarOpinionBasico(op1);
        estado.agregarOpinionBasico(op1);
        estado.agregarOpinionBasico(op2);
        estado.agregarOpinionBasico(op3);
        estado.agregarOpinionBasico(op3);
        assertEquals("No definido", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaGuasayana() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_GUASAYANA);
        estado.agregarOpinionBasico(op1);
        assertEquals("Vinchuca Guasayana", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsVinchucaInfestans() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_INFESTANS);
        estado.agregarOpinionBasico(op1);
        assertEquals("Vinchuca Infestans", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsVinchucaSordida() {
        when(op1.getTipo()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA);
        estado.agregarOpinionBasico(op1);
        assertEquals("Vinchuca Sordida", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsChinchaFoliada() {
        when(op1.getTipo()).thenReturn(TipoOpinion.CHINCHA_FOLIADA);
        estado.agregarOpinionBasico(op1);
        assertEquals("Chincha Foliada", estado.resultadoActual());
    }
    
    @Test
    public void testResultadoActualEsPhtiaChinche() {
        when(op1.getTipo()).thenReturn(TipoOpinion.PHTIA_CHINCHE);
        estado.agregarOpinionBasico(op1);
        assertEquals("Phtia Chinche", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsNinguna() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        estado.agregarOpinionBasico(op1);
        assertEquals("Ninguna", estado.resultadoActual());
    }

    @Test
    public void testResultadoActualEsImagenPocoClara() {
        when(op1.getTipo()).thenReturn(TipoOpinion.IMAGEN_POCO_CLARA);
        estado.agregarOpinionBasico(op1);
        assertEquals("Imagen poco clara", estado.resultadoActual());
    }

    @Test
    public void testAgregarOpinionExpertoEntoncesCambiaElTipoDeestado() {
        when(op1.getTipo()).thenReturn(TipoOpinion.NINGUNA);
        estado.agregarOpinionExperto(op1);
        verify(muestra).setEstado(any(MuestraExperto.class));
    }
}
