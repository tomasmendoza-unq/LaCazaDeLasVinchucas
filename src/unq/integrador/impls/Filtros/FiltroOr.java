package unq.integrador.impls.Filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

public class FiltroOr extends FiltroCompuesto {

    public FiltroOr(List<IFiltroMuestra> filtros) {
        super(filtros);
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return filtros.stream().anyMatch(filtroMuestra -> filtroMuestra.verificar(muestra));
    }
}
