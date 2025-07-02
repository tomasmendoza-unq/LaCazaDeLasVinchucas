package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.FiltroAnd;
import unq.integrador.impls.filtros.FiltroCompuesto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class FiltroAndTest {

    FiltroCompuesto filtroAnd;
    IFiltroMuestra filtro1;
    IFiltroMuestra filtro2;
    IMuestra muestra;
    IMuestra muestra2;
    List<IFiltroMuestra> filtros = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        filtro1 = mock(IFiltroMuestra.class);
        filtro2 = mock(IFiltroMuestra.class);

        filtros.add(filtro1);
        filtros.add(filtro2);
        filtroAnd = new FiltroAnd(filtros);
        muestra = mock(IMuestra.class);
        muestra2 = mock(IMuestra.class);
    }

    @Test
    public void muestra1PasaTodosLosFiltros(){
        when(filtro1.verificar(muestra)).thenReturn(true);
        when(filtro2.verificar(muestra)).thenReturn(true);


        assertTrue(filtroAnd.verificar(muestra));

        verify(filtro1).verificar(muestra);
        verify(filtro2).verificar(muestra);
    }

    @Test
    public void muestra2NoPasaTodosLosFiltros(){
        when(filtro1.verificar(muestra)).thenReturn(true);
        when(filtro2.verificar(muestra)).thenReturn(false);


        assertFalse(filtroAnd.verificar(muestra));

        verify(filtro1).verificar(muestra);
        verify(filtro2).verificar(muestra);
    }

}
