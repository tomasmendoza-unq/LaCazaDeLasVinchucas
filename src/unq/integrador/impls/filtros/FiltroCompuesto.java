package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

/**
 * Clase abstracta que funciona como filtro compuesto para las muestras
 * que necesiten cumpler más de una condición
 */
public abstract class FiltroCompuesto implements IFiltroMuestra {
    protected List<IFiltroMuestra> filtros;

    /**
     * Constructor de FiltroCompuesto
     * @param filtros Una lista de filtros que será aplicada a alguna muestra
     */
    public FiltroCompuesto(List<IFiltroMuestra> filtros) {
        this.filtros = filtros;
    }

    /**
     * Método que verifica si una muestra cumple con todos los filtros
     * @param muestra Una muestra a aplicarle los filtros para ver si pasa
     * @return {@code true} si la muestra cumple todos los filtros {@code false} si no los cumple
     */
    public abstract boolean verificar(IMuestra muestra);
}
