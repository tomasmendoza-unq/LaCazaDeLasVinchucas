package unq.integrador.impls.Filtros;

import unq.integrador.IMuestra;
import unq.integrador.enums.TipoOpinion;

public class FiltroTipoDeInsecto extends FiltroBasico{
    private TipoOpinion tipoOpinion;

    public FiltroTipoDeInsecto(TipoOpinion tipoOpinion) {
        this.tipoOpinion = tipoOpinion;
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.resultadoActual().equals(tipoOpinion.imprimirTipo());
    }
}
