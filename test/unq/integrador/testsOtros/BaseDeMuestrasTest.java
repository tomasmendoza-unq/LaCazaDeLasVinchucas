package unq.integrador.testsOtros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.impls.Sistema;

import static org.mockito.Mockito.*;

public class BaseDeMuestrasTest {

    ISistema sistema;
    IUsuario usuario;
    IMuestra muestra1;
    IUbicacion ubicacionMuestra;
    IZonaDeCobertura zonaDeCobertura;
    IZonaDeCobertura zonaDeCobertura2;

    @BeforeEach
    public void setUp(){
        sistema = new Sistema();
        usuario = mock(IUsuario.class);
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

        sistema.RegistrarZona(zonaDeCobertura);
        sistema.RegistrarZona(zonaDeCobertura2);

        sistema.cargarMuestra(muestra1,usuario);

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

        sistema.RegistrarZona(zonaDeCobertura);
        sistema.RegistrarZona(zonaDeCobertura2);

        sistema.notificarVerificacion(muestra1);

        verify(muestra1).getUbicacion();
        verify(zonaDeCobertura).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura2).contieneUbicacion(ubicacionMuestra);
        verify(zonaDeCobertura, never()).notificarNuevaMuestraVerificada(muestra1);
        verify(zonaDeCobertura2).notificarNuevaMuestraVerificada(muestra1);

    }

}
