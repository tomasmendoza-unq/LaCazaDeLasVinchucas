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
    IMuestra muestra;
    IUbicacion ubicacionMuestra;
    IZonaDeCobertura zonaDeCobertura;
    IZonaDeCobertura zonaDeCobertura2;

    @BeforeEach
    public void setUp(){
        baseDeMuestras = new BaseDeMuestras();
        muestra = mock(IMuestra.class);
        ubicacionMuestra = mock(IUbicacion.class);
        zonaDeCobertura = mock(IZonaDeCobertura.class);
        zonaDeCobertura2 = mock(IZonaDeCobertura.class);
    }

    @Test
    public void seCargaUnaMuestraALaBaseDeDatos(){
        when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
        when(zonaDeCobertura.contieneUbicacion(ubicacionMuestra)).thenReturn(false);
        when(zonaDeCobertura2.contieneUbicacion(ubicacionMuestra)).thenReturn(true);

        baseDeMuestras.RegistrarZona(zonaDeCobertura);
        baseDeMuestras.RegistrarZona(zonaDeCobertura2);

        baseDeMuestras.cargarMuestra(muestra);

        verify(muestra).getUbicacion();
        verify(zonaDeCobertura).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura2).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura, never()).cargarMuestra(muestra);
        verify(zonaDeCobertura2).cargarMuestra(muestra);

    }

    @Test
    public void seVerificaUnaMuestra(){
        when(muestra.getUbicacion()).thenReturn(ubicacionMuestra);
        when(zonaDeCobertura.contieneUbicacion(ubicacionMuestra)).thenReturn(false);
        when(zonaDeCobertura2.contieneUbicacion(ubicacionMuestra)).thenReturn(true);

        baseDeMuestras.RegistrarZona(zonaDeCobertura);
        baseDeMuestras.RegistrarZona(zonaDeCobertura2);

        baseDeMuestras.cargarMuestraVerificada(muestra);

        verify(muestra).getUbicacion();
        verify(zonaDeCobertura).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura2).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura, never()).notificarNuevaMuestraVerificada(muestra);
        verify(zonaDeCobertura2).notificarNuevaMuestraVerificada(muestra);

    }

}
