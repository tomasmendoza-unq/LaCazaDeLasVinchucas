package unq.integrador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.Enums.Opinion;
import unq.integrador.Exceptions.MuestraException;
import unq.integrador.Impls.UsuarioBasico;
import unq.integrador.Impls.UsuarioExperto;

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
        opinion = Opinion.Vinchuca;
    }


    @Test
    public void UsuarioBasicoOpinaSobreUnaMuestraExperto() {

        doThrow(new MuestraException()).when(muestra).agregarOpinionBasico(opinion);

        assertThrows(MuestraException.class, () -> {
            usuarioBasico.opinarSobreUnaMuestra(muestra,opinion);
        });

        verify(muestra).agregarOpinionBasico(opinion);

    }

    @Test
    public void UsuarioExpertoOpinaSobreUnaMuestraCerrada() {

        doThrow(new MuestraException()).when(muestra).agregarOpinionExperto(opinion);

        assertThrows(MuestraException.class, () -> {
            usuarioExperto.opinarSobreUnaMuestra(muestra,opinion);
        });

        verify(muestra).agregarOpinionExperto(opinion);

    }
}
