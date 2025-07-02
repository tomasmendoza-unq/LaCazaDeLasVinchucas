package unq.integrador.impls.filtros;

import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

public class FiltroPorUltimaFechaDeModificacion extends FiltroPorFecha{
    public FiltroPorUltimaFechaDeModificacion(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        super(estrategiaComparacionFecha);
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return estrategiaComparacionFecha.verificar(muestra.getFechaUltimaVotacion());
    }
}
