package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;
import unq.integrador.impls.filtros.FiltroPorUltimaFechaDeModificacion;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class FiltroPorUltimaFechaDeModificacionTest {
    IFiltroMuestra filtro;
    IMuestra muestra;
    EstrategiaComparacionFecha estrategiaComparacionFecha;


    @BeforeEach
    public void setUp(){
        muestra = mock(IMuestra.class);
        estrategiaComparacionFecha = mock(EstrategiaComparacionFecha.class);
        filtro = new FiltroPorUltimaFechaDeModificacion(estrategiaComparacionFecha);
    }

    @Test
    public void muestraPasaElFiltro(){
        when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
        when(estrategiaComparacionFecha.verificar(LocalDate.now())).thenReturn(true);

        assertTrue(filtro.verificar(muestra));

        verify(estrategiaComparacionFecha).verificar(LocalDate.now());
    }

    @Test
    public void muestraPasaElFiltroConLapsoIgualPeroFallaLosOtros(){
        when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
        when(estrategiaComparacionFecha.verificar(LocalDate.now())).thenReturn(false);

        assertFalse(filtro.verificar(muestra));

        verify(estrategiaComparacionFecha).verificar(LocalDate.now());
    }


}
