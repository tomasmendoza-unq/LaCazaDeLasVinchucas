package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

public abstract class FiltroPorFecha implements IFiltroMuestra {
    protected EstrategiaComparacionFecha estrategiaComparacionFecha;

    public FiltroPorFecha(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        this.estrategiaComparacionFecha = estrategiaComparacionFecha;
    }
}
