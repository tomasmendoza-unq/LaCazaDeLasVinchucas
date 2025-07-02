package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

public class FechaDespues extends EstrategiaComparacionFecha{

    public FechaDespues(LocalDate fecha) {
        super(fecha);
    }

    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isBefore(fechaComparar);
    }
}
