package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

/**
 * Estrategia para saber si una fecha viene antes que otra
 */
public class FechaAntes extends EstrategiaComparacionFecha {

    /**
     * Constructor de FechaAntes
     * 
     * @param fecha La fecha propia a comparar
     */
    public FechaAntes(LocalDate fecha) {
        super(fecha);
    }

    /**
     * Indica si la fecha dada vino después de la fecha propia
     * 
     * @param fechaComparar Una fecha a comparar
     * @return {@code true} si la fecha pasada es más nueva que la propia; {@code false} Si son iguales o se creó antes
     */
    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isAfter(fechaComparar);
    }
}
