package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
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
        assertDoesNotThrow(() -> usuarioRango.opinarSobreUnaMuestra(muestra,opinion));

        verify(muestra).agregarOpinionExperto(opinion);
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

        doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionExperto(opinion);

        assertThrows(SinAccesoAMuestraException.class, () -> {
            usuarioRango.opinarSobreUnaMuestra(muestra,opinion);
        });
    }

}
