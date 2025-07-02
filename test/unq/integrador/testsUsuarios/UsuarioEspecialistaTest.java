package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.*;
import unq.integrador.impls.usuario.Usuario;
import unq.integrador.impls.usuario.UsuarioEspecialista;
import unq.integrador.impls.usuario.UsuarioExperto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UsuarioEspecialistaTest {
    UsuarioExperto usuarioRango;
    IMuestra muestra;
    Opinion opinion;
    IUsuario usuario;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        usuarioRango = new UsuarioEspecialista();
        usuario = mock(Usuario.class);
        opinion = mock(Opinion.class);
    }

    @Test
    public void UsuarioEspecialistaOpinaSobreUnaMuestraLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
        assertDoesNotThrow(() -> usuarioRango.opinarSobreUnaMuestra(muestra,opinion, usuario));

        verify(muestra).agregarOpinionExperto(usuario,opinion);
    }

    @Test
    public void UsuarioNoBajaDeRango(){
        when(usuario.subeDeRango()).thenReturn(true);

        usuarioRango.determinarSiguienteRango(usuario);

        verify(usuario).setProximoRango(usuarioRango);

    }

    @Test
    public void UsuarioDebeBajarARangoBasico(){
        when(usuario.subeDeRango()).thenReturn(false);

        usuarioRango.determinarSiguienteRango(usuario);

        verify(usuario).setProximoRango(usuarioRango);

    }


    @Test
    public void UsuarioEspecialistaOpinaSobreUnaMuestraCerrada() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

        doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionExperto(usuario, opinion);

        assertThrows(SinAccesoAMuestraException.class, () -> {
            usuarioRango.opinarSobreUnaMuestra(muestra,opinion, usuario);
        });
    }

}
