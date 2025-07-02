package unq.integrador.testsUsuarios;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.usuario.Usuario;
import unq.integrador.impls.usuario.UsuarioBasico;
import unq.integrador.impls.usuario.UsuarioExperto;

public class UsuarioExpertoTest {
  UsuarioExperto usuarioRango;
  IMuestra muestra;
  Opinion opinion;
  IUsuario usuario;

  @BeforeEach
  public void setUp() {
    muestra = mock(IMuestra.class);
    usuarioRango = new UsuarioExperto();
    usuario = mock(Usuario.class);
    opinion = mock(Opinion.class);
  }

  @Test
  public void UsuarioExpertoOpinaSobreUnaMuestraLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException {
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

    verify(usuario).setProximoRango(isA(UsuarioBasico.class));

  }


  @Test
  public void UsuarioExpertoOpinaSobreUnaMuestraCerrada() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException {

    doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionExperto(usuario,opinion);

    assertThrows(SinAccesoAMuestraException.class, () -> {
      usuarioRango.opinarSobreUnaMuestra(muestra,opinion, usuario);
    });
  }
}
