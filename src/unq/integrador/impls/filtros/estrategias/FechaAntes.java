package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

import java.time.LocalDate;

public class FechaAntes extends EstrategiaComparacionFecha{

    public FechaAntes(LocalDate fecha) {
        super(fecha);
    }

    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isAfter(fechaComparar);
    }
}
