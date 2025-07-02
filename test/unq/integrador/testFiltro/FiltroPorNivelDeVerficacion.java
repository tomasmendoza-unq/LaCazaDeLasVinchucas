package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaVerificacion;
import unq.integrador.impls.filtros.FiltroPorNivelDeVerificacion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FiltroPorNivelDeVerficacion {
    IFiltroMuestra filtro;
    EstrategiaVerificacion estrategiaVerificacion;
    IMuestra muestra;


    @BeforeEach
    public void setUp(){
        muestra = mock(IMuestra.class);
        estrategiaVerificacion = mock(EstrategiaVerificacion.class);
        filtro = new FiltroPorNivelDeVerificacion(estrategiaVerificacion);
    }

    @Test
    public void muestraPasaElFiltro(){
        when(estrategiaVerificacion.verificar(muestra)).thenReturn(true);

        assertTrue(filtro.verificar(muestra));

        verify(estrategiaVerificacion).verificar(muestra);
    }

    @Test
    public void muestraNoPasaElFiltro(){
        when(estrategiaVerificacion.verificar(muestra)).thenReturn(false);

        assertFalse(filtro.verificar(muestra));

        verify(estrategiaVerificacion).verificar(muestra);
    }

}
