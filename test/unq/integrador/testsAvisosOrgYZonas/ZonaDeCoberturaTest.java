package unq.integrador.testsAvisosOrgYZonas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IMuestra;
import unq.integrador.IOrganizacion;
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
    IOrganizacion organizacion1;
    IOrganizacion organizacion2;
    IMuestra muestra;
    IUbicacion ubicacionMuestra;

    @BeforeEach
    public void setUp(){
        organizacion1 = mock(IOrganizacion.class);
        organizacion2 = mock(IOrganizacion.class);
        epicentro1 = mock(IUbicacion.class);
        zonaDeCobertura1 = new ZonaDeCobertura(epicentro1, "Ezpeleta",10);
        epicentro2 = mock(IUbicacion.class);
        ubicacionMuestra = mock(IUbicacion.class);
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
    public void zona1NotificaQueSePublicoUnaMuestraYNotificaSoloAOrg1(){

        zonaDeCobertura1.registrarOrganizacion(organizacion1);
        zonaDeCobertura1.registrarOrganizacion(organizacion2);
        zonaDeCobertura1.eliminarOrganizacion(organizacion2);
        zonaDeCobertura1.cargarMuestra(muestra);


        verify(organizacion1).recibirNotificacionMuestra(zonaDeCobertura1, muestra);
        verify(organizacion2, never()).recibirNotificacionMuestra(zonaDeCobertura1,muestra);
    }

    @Test
    public void zona1ContieneLaUbicacionDeLaMuestra(){
        when(epicentro1.distanciaA(ubicacionMuestra)).thenReturn(5d);

        assertTrue(zonaDeCobertura1.contieneUbicacion(ubicacionMuestra));
        verify(epicentro1).distanciaA(ubicacionMuestra);
    }

    @Test
    public void zona2NoContieneLaUbicacionDeLaMuestra(){
        when(epicentro2.distanciaA(ubicacionMuestra)).thenReturn(90d);

        assertFalse(zonaDeCobertura2.contieneUbicacion(ubicacionMuestra));
        verify(epicentro2).distanciaA(ubicacionMuestra);
    }


}
