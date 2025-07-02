package unq.integrador.testsUsuarios;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.UsuarioBasico;
import unq.integrador.impls.UsuarioExperto;
import unq.integrador.impls.UsuarioRango;

public class UsuarioBasicoTest {
  UsuarioRango usuarioRango;
  IMuestra muestra;
  Opinion opinion;
  IUsuario usuario;

  @BeforeEach
  public void setUp() {
    usuario = mock(IUsuario.class);
    muestra = mock(IMuestra.class);
    opinion = mock(Opinion.class);
    usuarioRango = new UsuarioBasico();
  }

  @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

      assertDoesNotThrow(() -> usuarioRango.opinarSobreUnaMuestra(muestra, opinion, usuario));

      verify(muestra).agregarOpinionBasico(usuario,opinion);
    }

    @Test
    public void UsuarioDebeSubirARangoExperto(){
      when(usuario.subeDeRango()).thenReturn(true);


      usuarioRango.determinarSiguienteRango(usuario);

      verify(usuario).setProximoRango(isA(UsuarioExperto.class));

    }

  @Test
  public void UsuarioNoSubeDeRango(){
    when(usuario.subeDeRango()).thenReturn(false);


    usuarioRango.determinarSiguienteRango(usuario);

    verify(usuario).setProximoRango(usuarioRango);

  }

    @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraNoLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

    doThrow(new SinAccesoAMuestraException())
                  .when(muestra)
                  .agregarOpinionBasico(usuario,opinion);


      assertThrows(SinAccesoAMuestraException.class, 
        () -> usuarioRango.opinarSobreUnaMuestra(muestra, opinion, usuario));

      assertDoesNotThrow(() ->verify(muestra).agregarOpinionBasico(usuario,opinion));
    }
}
