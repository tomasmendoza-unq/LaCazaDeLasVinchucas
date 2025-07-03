package unq.integrador.impls.filtros.estrategias;

import java.time.LocalDate;

/**
 * Estrategia abstracta para que hereden comportamiento
 * FechaAntes, FechaDespues y FechaIgual
 * @see FechaAntes
 * @see FechaDespues
 * @see FechaIgual
 */
public abstract class EstrategiaComparacionFecha {
    protected LocalDate fecha;

    public EstrategiaComparacionFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public abstract boolean verificar(LocalDate fechaComparar);
}
