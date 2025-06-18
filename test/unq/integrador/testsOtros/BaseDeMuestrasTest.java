package unq.integrador.testsOtros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.impls.BaseDeMuestras;

import static org.mockito.Mockito.*;

public class BaseDeMuestrasTest {

    IBaseDeMuestras baseDeMuestras;
    IMuestra muestra1;
    IUbicacion ubicacionMuestra;
    IZonaDeCobertura zonaDeCobertura;
    IZonaDeCobertura zonaDeCobertura2;

    @BeforeEach
    public void setUp(){
        baseDeMuestras = new BaseDeMuestras();
        muestra1 = mock(IMuestra.class);
        ubicacionMuestra = mock(IUbicacion.class);
        zonaDeCobertura = mock(IZonaDeCobertura.class);
        zonaDeCobertura2 = mock(IZonaDeCobertura.class);
    }

    @Test
    public void seCargaUnaMuestraALaBaseDeDatos(){
        when(muestra1.getUbicacion()).thenReturn(ubicacionMuestra);
        when(zonaDeCobertura.contieneUbicacion(ubicacionMuestra)).thenReturn(false);
        when(zonaDeCobertura2.contieneUbicacion(ubicacionMuestra)).thenReturn(true);

        baseDeMuestras.RegistrarZona(zonaDeCobertura);
        baseDeMuestras.RegistrarZona(zonaDeCobertura2);

        baseDeMuestras.cargarMuestra(muestra1);

        verify(muestra1).getUbicacion();
        verify(zonaDeCobertura).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura2).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura, never()).cargarMuestra(muestra1);
        verify(zonaDeCobertura2).cargarMuestra(muestra1);

    }

    @Test
    public void seVerificaUnaMuestra(){
        when(muestra1.getUbicacion()).thenReturn(ubicacionMuestra);
        when(zonaDeCobertura.contieneUbicacion(ubicacionMuestra)).thenReturn(false);
        when(zonaDeCobertura2.contieneUbicacion(ubicacionMuestra)).thenReturn(true);

        baseDeMuestras.RegistrarZona(zonaDeCobertura);
        baseDeMuestras.RegistrarZona(zonaDeCobertura2);

        baseDeMuestras.cargarMuestraVerificada(muestra1);

        verify(muestra1).getUbicacion();
        verify(zonaDeCobertura).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura2).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura, never()).notificarNuevaMuestraVerificada(muestra1);
        verify(zonaDeCobertura2).notificarNuevaMuestraVerificada(muestra1);

    }

}
