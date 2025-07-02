package unq.integrador.impls.filtros;

import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

public class FiltroPorFechaDeCreacion extends FiltroPorFecha{

    public FiltroPorFechaDeCreacion(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        super(estrategiaComparacionFecha);
    }


    @Override
    public boolean verificar(IMuestra muestra) {
        return estrategiaComparacionFecha.verificar(muestra.getFechaCreacion());
    }
}
