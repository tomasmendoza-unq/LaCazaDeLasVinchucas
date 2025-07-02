package unq.integrador.impls;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

public class FiltroAnd implements IFiltroMuestra {
    List<IFiltroMuestra> filtros;

    @Override
    public boolean test(IMuestra muestra) {
        return filtros.stream().allMatch(filtroMuestra -> filtroMuestra.test(muestra));
    }
}
