package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

/**
 * Estrategia para saber si una fecha es igual a otra
 */
public class FechaIgual extends EstrategiaComparacionFecha {
    
    /**
     * Constructor de FechaIgual
     * @param fecha La fecha propia a comparar
     */
    public FechaIgual(LocalDate fecha) {
        super(fecha);
    }

    /**
     * Indica si la fecha dada es igual a la fecha propia
     * 
     * @param fechaComparar Una fecha a comparar
     * @return {@code true} si las dos fechas son iguales; {@code false} Si no son iguales
     */
    @Override
    public boolean verificar(LocalDate fechaComparar) {
        return fecha.isEqual(fechaComparar);
    }
}
