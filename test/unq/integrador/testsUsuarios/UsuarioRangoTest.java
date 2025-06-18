package unq.integrador.testsUsuarios;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.UsuarioBasico;
import unq.integrador.impls.UsuarioExperto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UsuarioRangoTest {



    UsuarioExperto usuarioExperto;
    UsuarioBasico usuarioBasico;
    IMuestra muestra;
    IBaseDeMuestras baseDeMuestras;
    Opinion opinion;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        usuarioBasico = new UsuarioBasico();
        usuarioExperto = new UsuarioExperto();
        opinion = mock(Opinion.class);
    }


    @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraExperto() {

        doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionBasico(opinion);

        assertThrows(SinAccesoAMuestraException.class, () -> {
            usuarioBasico.opinarSobreUnaMuestra(muestra,opinion);
        });

        verify(muestra).agregarOpinionBasico(opinion);

    }

    @Test
    public void UsuarioExpertoOpinaSobreUnaMuestraCerrada() {

        doThrow(new SinAccesoAMuestraException()).when(muestra).agregarOpinionExperto(opinion);

        assertThrows(SinAccesoAMuestraException.class, () -> {
            usuarioExperto.opinarSobreUnaMuestra(muestra,opinion);
        });


    }
}
