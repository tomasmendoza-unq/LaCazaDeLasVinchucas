package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.FiltroOr;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class FiltroOrTest {
    IFiltroMuestra filtroOr;
    IFiltroMuestra filtro1;
    IFiltroMuestra filtro2;
    IMuestra muestra;
    List<IFiltroMuestra> filtros = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        filtro1 = mock(IFiltroMuestra.class);
        filtro2 = mock(IFiltroMuestra.class);

        filtros.add(filtro1);
        filtros.add(filtro2);

        filtroOr = new FiltroOr(filtros);

        muestra = mock(IMuestra.class);
    }

    @Test
    public void muestra1PasaElFiltro1PeroNoElFiltro2(){
        when(filtro1.verificar(muestra)).thenReturn(true);
        when(filtro2.verificar(muestra)).thenReturn(false);


        assertTrue(filtroOr.verificar(muestra));

        verify(filtro1).verificar(muestra);
        verify(filtro2, never()).verificar(muestra);
    }

    @Test
    public void muestra1PasaTodosLosFiltros(){
        when(filtro1.verificar(muestra)).thenReturn(true);
        when(filtro2.verificar(muestra)).thenReturn(true);


        assertTrue(filtroOr.verificar(muestra));

        verify(filtro1).verificar(muestra);
        verify(filtro2, never()).verificar(muestra); //por shortCircuit sucede
    }

    @Test
    public void muestra1NoPasaTodosLosFiltros(){
        when(filtro1.verificar(muestra)).thenReturn(false);
        when(filtro2.verificar(muestra)).thenReturn(false);


        assertFalse(filtroOr.verificar(muestra));

        verify(filtro1).verificar(muestra);
        verify(filtro2).verificar(muestra);
    }

}
