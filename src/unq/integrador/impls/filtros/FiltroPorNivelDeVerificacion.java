package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaVerificacion;

public class FiltroPorNivelDeVerificacion implements IFiltroMuestra {
    private EstrategiaVerificacion estrategiaVerificacion;

    public FiltroPorNivelDeVerificacion(EstrategiaVerificacion estrategiaVerificacion) {
        this.estrategiaVerificacion = estrategiaVerificacion;
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return estrategiaVerificacion.verificar(muestra);
    }
}
