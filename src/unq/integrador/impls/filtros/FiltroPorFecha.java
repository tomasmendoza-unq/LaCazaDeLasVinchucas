package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

/**
 * Clase de filtros de la cual heredan comportamiento 
 * FiltroPorUltimaFechaDeModificacion y FiltroPorFechaDeCreacion
 */
public abstract class FiltroPorFecha implements IFiltroMuestra {
    protected EstrategiaComparacionFecha estrategiaComparacionFecha;

    /**
     * Constructor de los filtros por fechas
     * @param estrategiaComparacionFecha Una estrategia por la cual se filtra (Antes, igual o después)
     */
    public FiltroPorFecha(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        this.estrategiaComparacionFecha = estrategiaComparacionFecha;
    }
}
