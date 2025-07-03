package unq.integrador.impls.filtros;

import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaVerificacion;

/**
 * Filtro para muestras en base a el nivel de verificación que tengan
 */
public class FiltroPorNivelDeVerificacion implements IFiltroMuestra {
    private EstrategiaVerificacion estrategiaVerificacion;

    /**
     * Constructor del filtro
     * @param estrategiaVerificacion Estrategia por la cual se filtra a las muestras.
     * Puede ser para las verificadas o las votadas.
     */
    public FiltroPorNivelDeVerificacion(EstrategiaVerificacion estrategiaVerificacion) {
        this.estrategiaVerificacion = estrategiaVerificacion;
    }

    /**
     * Filtro para separar las muestras que sean
     * verificadas o votadas según la estrategia utilizada
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return estrategiaVerificacion.verificar(muestra);
    }
}
