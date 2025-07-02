package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

public class FechaIgual extends EstrategiaComparacionFecha{
    public FechaIgual(LocalDate fecha) {
        super(fecha);
    }

    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isEqual(fechaComparar);
    }
}
