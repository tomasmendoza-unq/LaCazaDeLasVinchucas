package unq.integrador.testFiltro;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaVerificacion;
import unq.integrador.impls.filtros.estrategias.Verificada;

public class VerificadaTest {
    IMuestra muestra;
    EstrategiaVerificacion estado;

    @BeforeEach
    public void setUp() {
        muestra = mock(IMuestra.class);
        estado = new Verificada();
    }

    @Test
    public void testLaMuestraEsVerificada() {
        when(muestra.esVerificada()).thenReturn(true);
        assertTrue(estado.verificar(muestra));
    }

    @Test
    public void testLaMuestraEsVotadaEntoncesNoPasaLaEstrategia() {
        when(muestra.esVerificada()).thenReturn(false);
        assertFalse(estado.verificar(muestra));
    }
}
