package unq.integrador.testsAvisosOrgYZonas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.impls.ZonaDeCobertura;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ZonaDeCoberturaTest {

    IUbicacion epicentro1;
    IUbicacion epicentro2;
    IZonaDeCobertura zonaDeCobertura1;
    IZonaDeCobertura zonaDeCobertura2;
    IMuestra muestra;

    @BeforeEach
    public void setUp(){
        epicentro1 = mock(IUbicacion.class);
        zonaDeCobertura1 = new ZonaDeCobertura(epicentro1, "Ezpeleta",10);
        epicentro2 = mock(IUbicacion.class);
        zonaDeCobertura2 = new ZonaDeCobertura(epicentro2, "Berazategui",30);
    }

    @Test
    public void seLePideElNombreADistintasZonas(){
        assertEquals("Ezpeleta", zonaDeCobertura1.getNombreDeCobertura());
        assertNotEquals("Ezpeleta", zonaDeCobertura2.getNombreDeCobertura());
    }

    @Test
    public void zonaDeCoberturaBerazateguiSeSolapaConZonaDeCoberturaEzpeleta(){
        when(epicentro1.distanciaA(epicentro2)).thenReturn(5d);

        assertTrue(zonaDeCobertura1.seSolapaCon(zonaDeCobertura2));

        verify(epicentro1).distanciaA(epicentro2);
    }

    @Test
    public void zonaDeCoberturaBerazateguiNoSeSolapaConZonaDeCoberturaEzpeleta(){
        when(epicentro1.distanciaA(epicentro2)).thenReturn(90d);

        assertFalse(zonaDeCobertura1.seSolapaCon(zonaDeCobertura2));

        verify(epicentro1).distanciaA(epicentro2);
    }

    @Test
    public void sePuedeCargarMuestra(){

        zonaDeCobertura1.cargarMuestra(muestra);

        IZonaDeCobertura spyZona = spy(zonaDeCobertura1);
        spyZona.cargarMuestra(muestra);

        verify(spyZona).cargarMuestra(muestra);
    }

}
