package unq.integrador.testFiltro;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaVerificacion;
import unq.integrador.impls.filtros.estrategias.Votada;

public class VotadaTest {
    IMuestra muestra;
    EstrategiaVerificacion estado;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        estado = new Votada();
    }

    @Test
    public void testLaMuestraEsVotada() {
        when(muestra.esVerificada()).thenReturn(false);
        assertTrue(estado.verificar(muestra));
    }

    @Test
    public void testLaMuestraEsVerificadaEntoncesNoPasaLaEstrategia() {
        when(muestra.esVerificada()).thenReturn(true);
        assertFalse(estado.verificar(muestra));
    }
}
