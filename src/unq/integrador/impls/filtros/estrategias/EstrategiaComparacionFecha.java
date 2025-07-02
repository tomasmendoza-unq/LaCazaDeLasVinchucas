package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

import java.time.LocalDate;

public abstract class EstrategiaComparacionFecha {
    protected LocalDate fecha;

    public EstrategiaComparacionFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public abstract boolean verificar(LocalDate fechaComparar);
}
