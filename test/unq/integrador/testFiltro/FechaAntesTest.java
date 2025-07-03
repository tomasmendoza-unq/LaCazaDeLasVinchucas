package unq.integrador.testFiltro;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;
import unq.integrador.impls.filtros.estrategias.FechaAntes;

public class FechaAntesTest {
    EstrategiaComparacionFecha fecha;

    @BeforeEach
    public void setUp() {
        fecha = new FechaAntes(LocalDate.now());
    }

    @Test
    public void testLaFechaAVerificarEsAntes() {
        assertTrue(fecha.verificar(LocalDate.now().minusDays(10)));
    }
    
    @Test
    public void testLaFechaAVerificarEsLaMismaEntoncesFalla() {
        assertFalse(fecha.verificar(LocalDate.now()));
    }

    @Test
    public void testLaFechaAVerificarEsDespuesEntoncesFalla() {
        assertFalse(fecha.verificar(LocalDate.now().plusDays(10)));
    }
}
