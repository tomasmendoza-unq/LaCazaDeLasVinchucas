package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

/**
 * Estrategia para denotar que una muestra es votada
 */
public class Votada extends EstrategiaVerificacion {
    
    /**
     * Indica que una muestra es votada
     * 
     * @param muestra Una muestra para conocer su estado
     * @return {@code true} si la muestra es votada. {@code false} si la muestra es verificada
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return !muestra.esVerificada();
    }
}
