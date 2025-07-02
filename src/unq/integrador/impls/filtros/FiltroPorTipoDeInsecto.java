package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.enums.TipoOpinion;

public class FiltroPorTipoDeInsecto implements IFiltroMuestra {
    private TipoOpinion tipoOpinion;

    public FiltroPorTipoDeInsecto(TipoOpinion tipoOpinion) {
        this.tipoOpinion = tipoOpinion;
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.resultadoActual().equals(tipoOpinion.imprimirTipo());
    }
}
