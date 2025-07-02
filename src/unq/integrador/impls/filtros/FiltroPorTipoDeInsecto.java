package unq.integrador.impls.filtros;

import unq.integrador.IMuestra;
import unq.integrador.enums.TipoOpinion;

public class FiltroPorTipoDeInsecto extends FiltroBasico{
    private TipoOpinion tipoOpinion;

    public FiltroPorTipoDeInsecto(TipoOpinion tipoOpinion) {
        this.tipoOpinion = tipoOpinion;
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.resultadoActual().equals(tipoOpinion.imprimirTipo());
    }
}
