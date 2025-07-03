package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;
import unq.integrador.impls.filtros.estrategias.FechaIgual;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

public class FechaIgualTest {

    EstrategiaComparacionFecha fechaIgual;

    @BeforeEach
    public void setUp(){
        fechaIgual = new FechaIgual(LocalDate.now());
    }

    @Test
    public void testLaFechaEsIgualALaDeHoy() {
        assertTrue(fechaIgual.verificar(LocalDate.now()));
    }

    @Test
    public void testLaFechaEsNoIgualALaDeHoy() {
        assertFalse(fechaIgual.verificar(LocalDate.of(2018, 9, 12)));
    }



}
