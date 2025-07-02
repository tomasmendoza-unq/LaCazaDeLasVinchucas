package unq.integrador.impls.filtros;

import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

public abstract class FiltroPorFecha extends FiltroBasico{
    protected EstrategiaComparacionFecha estrategiaComparacionFecha;

    public FiltroPorFecha(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        this.estrategiaComparacionFecha = estrategiaComparacionFecha;
    }
}
