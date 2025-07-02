package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.filtros.FiltroPorTipoDeInsecto;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FiltroPorTipoDeInsectoTest {

    IFiltroMuestra filtro;
    IMuestra muestra;


    @BeforeEach
    public void setUp(){
        muestra = mock(IMuestra.class);
        filtro = new FiltroPorTipoDeInsecto(TipoOpinion.CHINCHA_FOLIADA);
    }

    @Test
    public void muestraPasaElFiltro(){
        when(muestra.resultadoActual()).thenReturn(TipoOpinion.CHINCHA_FOLIADA.imprimirTipo());

        assertTrue(filtro.verificar(muestra));

        verify(muestra).resultadoActual();
    }

    @Test
    public void muestraNoPasaElFiltro(){
        when(muestra.resultadoActual()).thenReturn(TipoOpinion.VINCHUCA_SORDIDA.imprimirTipo());

        assertFalse(filtro.verificar(muestra));

        verify(muestra).resultadoActual();
    }

}
