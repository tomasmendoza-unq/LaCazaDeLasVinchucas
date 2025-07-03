package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.enums.TipoOpinion;

/**
 * Filtro para las muestras en base al tipo de insecto detectado de la misma
 */
public class FiltroPorTipoDeInsecto implements IFiltroMuestra {
    private TipoOpinion tipoOpinion;

    /**
     * Constructor del filtros
     * @param tipoOpinion El tipo de insecto por el cual se filtra
     */
    public FiltroPorTipoDeInsecto(TipoOpinion tipoOpinion) {
        this.tipoOpinion = tipoOpinion;
    }

    /**
     * Si la muestra se detectó el mismo tipo de insecto del filtro, entonces
     * el resultado es verdadero.
     * @param muestra Una muestra por la que comparar por su resultado actual
     * @return {@code true} Si el resultado es igual al tipo de insecto de la muestra. 
     * Si no la cumple {@code false}
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.resultadoActual().equals(tipoOpinion.imprimirTipo());
    }
}
