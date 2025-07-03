package unq.integrador.impls.filtros;

import unq.integrador.IMuestra;
import unq.integrador.impls.filtros.estrategias.EstrategiaComparacionFecha;

/**
 * Filtro en base a la fecha de la última votación de la muestra dada
 */
public class FiltroPorUltimaFechaDeModificacion extends FiltroPorFecha {

    /**
     * Constructor del filtro
     * @param estrategiaComparacionFecha Una estrategia por la cual se compara 
     * (Si es antes, igual o después)
     */
    public FiltroPorUltimaFechaDeModificacion(EstrategiaComparacionFecha estrategiaComparacionFecha) {
        super(estrategiaComparacionFecha);
    }

    /**
     * Filtro para seleccionar las muestras que cumplan la estrategia interna de fecha
     * 
     * @param muestra Una muestra para compara por su fecha de ultima votación
     * @return {@code true} Si la muestra cumple con la estrategia interna. 
     * Si no la cumple {@code false}
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return estrategiaComparacionFecha.verificar(muestra.getFechaUltimaVotacion());
    }
}
