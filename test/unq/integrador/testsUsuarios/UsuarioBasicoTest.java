package unq.integrador.testsUsuarios;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.IUsuarioRango;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.UsuarioBasico;

public class UsuarioBasicoTest {
  IUsuarioRango usuario;
  IMuestra muestra;
  Opinion opinion;

  @BeforeEach
  public void setUp() {
    muestra = mock(IMuestra.class);
    opinion = mock(Opinion.class);
    usuario = new UsuarioBasico();
  }

  @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

      assertDoesNotThrow(() -> usuario.opinarSobreUnaMuestra(muestra, opinion));

      verify(muestra).agregarOpinionBasico(opinion);
    }

    @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraNoLibre() throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {

    doThrow(new SinAccesoAMuestraException())
                  .when(muestra)
                  .agregarOpinionBasico(opinion);


      assertThrows(SinAccesoAMuestraException.class, 
        () -> usuario.opinarSobreUnaMuestra(muestra, opinion));

      assertDoesNotThrow(() ->verify(muestra).agregarOpinionBasico(opinion));
    }
}
