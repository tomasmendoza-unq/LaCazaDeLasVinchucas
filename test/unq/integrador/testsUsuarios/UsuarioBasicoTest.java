package unq.integrador.testsUsuarios;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.UsuarioBasico;

public class UsuarioBasicoTest {
  UsuarioBasico usuario;
  IMuestra muestra;
  Opinion opinion;

  @BeforeEach
  public void setUp() {
    muestra = mock(IMuestra.class);
    usuario = new UsuarioBasico();
    opinion = mock(Opinion.class);
  }

  @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraLibre() {
      usuario.opinarSobreUnaMuestra(muestra,opinion);

      verify(muestra).agregarOpinionBasico(opinion);
    }

    @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraNoLibre() {

      doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionBasico(opinion);

      assertThrows(SinAccesoAMuestraException.class, () -> {
        usuario.opinarSobreUnaMuestra(muestra,opinion);
      });

      verify(muestra).agregarOpinionBasico(opinion);
    }
}
