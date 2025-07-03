package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

/**
 * Filtro que se aplica la operación lógica AND a varios filtros
 */
public class FiltroAnd extends FiltroCompuesto {

    /**
     * Constructor de FiltroAnd
     * @param filtros Una lista de filtros que que se aplican
     */
    public FiltroAnd(List<IFiltroMuestra> filtros) {
        super(filtros);
    }

    /**
     * Método que verifica si una muestra cumple con todos los filtros aplicando la operación AND
     * @param muestra Una muestra a aplicarle los filtros para ver si pasa
     * @return {@code true} si la muestra cumple todos los filtros {@code false} si no al menos uno
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return filtros.stream().allMatch(filtroMuestra -> filtroMuestra.verificar(muestra));
    }
}
