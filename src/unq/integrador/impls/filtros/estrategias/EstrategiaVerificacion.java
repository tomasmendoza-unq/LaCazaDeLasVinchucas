package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

/**
 * Estrategia abstracta para que hereden comportamiento
 * Votada y Verificada.
 * @see Votada
 * @see Verificada
 */
public abstract class EstrategiaVerificacion {
    public abstract boolean verificar(IMuestra muestra);
}
