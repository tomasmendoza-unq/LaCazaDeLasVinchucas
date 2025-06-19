package unq.integrador.impls;

import java.util.function.Predicate;

import unq.integrador.IMuestra;

/**
 * Clase que representa los filtros que se pueden aplicar a muetras
 * Estas mismas se pueden concatenar con los operadores OR y AND
 */
public class FiltroMuestras {
    private Predicate<IMuestra> condicion;

    /**
     * Constructor del FiltroMuestras
     * 
     * @param condicion Condición que se aplica para filtrar para quienes cumplen
     */
    public FiltroMuestras(Predicate<IMuestra> condicion) {
        this.condicion = condicion;
    }

    /**
     * Método para indicar si la muestra cumple la condición
     * 
     * @param muestra una muestra para ver si filtra
     * @return true si la muestra cumple la condición, `false` sino la cumple
     */
    public boolean test(IMuestra muestra) {
        return condicion.test(muestra);
    }

    /**
     * Método para aplicar el operador lógico AND a dos
     * filtros
     * 
     * @param otro Otro filtro para unir
     * @return Un filtro nuevo que posee la conjución de ambos filtros
     */
    public FiltroMuestras and(FiltroMuestras otro) {
        return new FiltroMuestras(condicion.and(otro.condicion));
    }

    /**
     * Método para aplicar el operador lógico OR a dos
     * filtros
     * 
     * @param otro Otro filtro para unir
     * @return Un filtro nuevo que posee la disyunción de ambos filtros
     */
    public FiltroMuestras or(FiltroMuestras otro) {
        return new FiltroMuestras(condicion.or(otro.condicion));
    }

    /**
     * Getter de de la condición del filtro
     * 
     * @return La condición del filtro
     */
    public Predicate<IMuestra> getPredicate() {
        return condicion;
    }
}
