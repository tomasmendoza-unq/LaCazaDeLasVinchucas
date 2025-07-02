package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

public abstract class FiltroBasico implements IFiltroMuestra {

    @Override
    public abstract boolean verificar(IMuestra muestra) ;


}
