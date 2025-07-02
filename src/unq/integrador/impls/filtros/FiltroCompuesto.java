package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import java.util.List;

public abstract class FiltroCompuesto implements IFiltroMuestra{
    protected List<IFiltroMuestra> filtros;

    public FiltroCompuesto(List<IFiltroMuestra> filtros) {
        this.filtros = filtros;
    }


    public abstract boolean verificar(IMuestra muestra);
}
