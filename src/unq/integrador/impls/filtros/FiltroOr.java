package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

/**
 * Filtro que se aplica la operación lógica OR a varios filtros
 */
public class FiltroOr extends FiltroCompuesto {

    /**
     * Constructor de FiltroOr
     * @param filtros Una lista de filtros que que se aplican
     */
    public FiltroOr(List<IFiltroMuestra> filtros) {
        super(filtros);
    }

    /**
     * Verifica si la muestra pasa por varios filtros aplicandoles la operación lógica OR
     * @param muestra Una muestra para aplicar varios filtros con operador OR
     * @return {@code true} si alguna muestra cumple con el filtro {@code false} en caso de que no
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return filtros.stream()
            .anyMatch(filtroMuestra -> filtroMuestra.verificar(muestra));
    }
}
