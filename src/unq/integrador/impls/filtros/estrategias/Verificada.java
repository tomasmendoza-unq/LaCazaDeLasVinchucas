package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

/**
 * Estrategia para denotar que una muestra es verificada
 */
public class Verificada extends EstrategiaVerificacion {
    
    /**
     * Indica que una muestra es verificada
     * 
     * @param muestra Una muestra para conocer su estado
     * @return {@code true} si la muestra es verificada. {@code false} si la muestra es votada
     */
    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.esVerificada();
    }
}
