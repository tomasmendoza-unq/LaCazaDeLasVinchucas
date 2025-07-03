package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

/**
 * Estrategia para saber si una fecha viene después de otra
 */
public class FechaDespues extends EstrategiaComparacionFecha {

    /**
     * Constructor de FechaDespues
     * @param fecha La fecha propia a comparar
     */
    public FechaDespues(LocalDate fecha) {
        super(fecha);
    }

    /**
     * Indica si la fecha dada vino antes que la fecha propia
     * 
     * @param fechaComparar Una fecha a comparar
     * @return {@code true} si la fecha pasada es más antigua que la propia; {@code false} Si son iguales o se creó después
     */
    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isBefore(fechaComparar);
    }
}
