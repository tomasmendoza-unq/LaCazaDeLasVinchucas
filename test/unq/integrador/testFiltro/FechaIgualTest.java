package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;
import unq.integrador.impls.filtros.estrategias.FechaIgual;

import java.time.LocalDate;

public class FechaIgualTest {

    EstrategiaComparacionFecha fechaIgual;

    @BeforeEach
    public void setUp(){
        fechaIgual = new FechaIgual(LocalDate.now());
    }
    
}
