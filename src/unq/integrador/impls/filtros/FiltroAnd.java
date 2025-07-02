package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

public class FiltroAnd extends FiltroCompuesto {
    public FiltroAnd(List<IFiltroMuestra> filtros) {
        super(filtros);
    }


    @Override
    public boolean verificar(IMuestra muestra) {
        return filtros.stream().allMatch(filtroMuestra -> filtroMuestra.verificar(muestra));
    }
}
