package unq.integrador.impls;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

public class FiltroOr implements IFiltroMuestra {
    List<IFiltroMuestra> filtros;

    @Override
    public boolean test(IMuestra muestra) {
        return filtros.stream().anyMatch(filtroMuestra -> filtroMuestra.test(muestra));
    }
}
