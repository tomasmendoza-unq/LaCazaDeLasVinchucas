package unq.integrador.testsOtros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.*;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.Sistema;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SistemaTest {

    ISistema sistema;
    IUsuario usuario;
    IMuestra muestra1;
    Opinion opinion;
    IFiltroMuestra filtro;
    IUbicacion ubicacionMuestra;
    IZonaDeCobertura zonaDeCobertura;
    IZonaDeCobertura zonaDeCobertura2;

    @BeforeEach
    public void setUp(){
        sistema = new Sistema();
        usuario = mock(IUsuario.class);
        muestra1 = mock(IMuestra.class);
        filtro = mock(IFiltroMuestra.class);
        opinion = mock(Opinion.class);
        ubicacionMuestra = mock(IUbicacion.class);
        zonaDeCobertura = mock(IZonaDeCobertura.class);
        zonaDeCobertura2 = mock(IZonaDeCobertura.class);
    }

    @Test
    public void seCargaUnaMuestraAlSistema(){
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
        verify(usuario).agregarMuestraPublicada(muestra1);

    }

    @Test
    public void usuarioOpinaSobreLaMuestra() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
        sistema.opinarSobre(muestra1,usuario,opinion);
        verify(usuario).opinarSobreUnaMuestra(muestra1,opinion);
    }

    @Test
    public void usuarioNoPuedeOpinarEnSuMuestra() throws Exception {
        doThrow(new UnUsuarioNoPuedeOpinarEnSuMuestraException())
                .when(usuario).opinarSobreUnaMuestra(muestra1, opinion);

        assertThrows(UnUsuarioNoPuedeOpinarEnSuMuestraException.class,
                () -> sistema.opinarSobre(muestra1, usuario, opinion)
        );
    }

    @Test
    public void usuarioNoPuedeOpinarNuevamenteSobreLaMismaMuestra() throws Exception {
        doThrow(new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException())
                .when(usuario).opinarSobreUnaMuestra(muestra1, opinion);

        assertThrows(UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException.class,
                () -> sistema.opinarSobre(muestra1, usuario, opinion)
        );
    }

    @Test
    public void usuarioNoTieneAccesoALaMuestra() throws Exception {
        doThrow(new SinAccesoAMuestraException())
                .when(usuario).opinarSobreUnaMuestra(muestra1, opinion);

        assertThrows(SinAccesoAMuestraException.class,
                () -> sistema.opinarSobre(muestra1, usuario, opinion)
        );
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

    @Test
    public void recibeUnFitro(){
        IMuestra muestra2 = mock(IMuestra.class);

        sistema.cargarMuestra(muestra1,usuario);
        sistema.cargarMuestra(muestra2,usuario);

        when(filtro.verificar(muestra1)).thenReturn(true);
        when(filtro.verificar(muestra2)).thenReturn(false);

        assertTrue(sistema.filtrarMuestras(filtro).contains(muestra1));
        assertFalse(sistema.filtrarMuestras(filtro).contains(muestra2));
    }

}
