package unq.integrador.impls.muestras;

import java.util.HashMap;

import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.Opinion;

/**
 * Esta clase representa las muestras libres, que son aquellas en
 * las que todos los usuarios pueden participar o, véase, no participó
 * ningún Experto todavía.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */

public class MuestraLibre implements IEstadoDeMuestra {
    private HashMap<TipoOpinion, Integer> opiniones;
    private IMuestra muestra;
    /**
     * Constructor de la clase MuestraLibre
     * @param muestra muestra la cual pertenece el estado
     */
    public MuestraLibre(IMuestra muestra) {
        this.opiniones = new HashMap<>();
        this.muestra = muestra;
    }

    /**
     * Método para conseguir el resultado actual de opiniones,
     * realizado con un HashMap para separar los distintos valores
     * en claves y por cada ocurrencia se suma uno.
     * 
     * @return La opinion con más votos, que sería la que más 
     * ocurrencias tiene en la lista de opiniones de la clase
     */
    @Override
    public String resultadoActual() {
        int max = opiniones.values().stream().max(Integer::compareTo).orElse(0);

        // contar cuántas opiniones tienen la cantidad máxima
        long cantidadMaximos = opiniones.values().stream().filter(v -> v == max).count();

        if (cantidadMaximos == 1) {
            // obtener la única clave con valor máximo
            return opiniones.keySet().stream()
                            .filter(op -> opiniones.get(op) == max)
                            .findFirst().orElse(null)
                            .imprimirTipo();
        }

        return "No definido"; // fallback
    }

    /**
     * Agregua una opinión al diccionario de Opiniones, agregando 1 al valor que estaba
     * o setteandolo en 1 si no existía.
     * 
     * @param op una opinión para agregar al diccionario
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        this.opiniones.put(op.getTipo(), this.opiniones.getOrDefault(op.getTipo(), 0) + 1);
    }

    /**
     * Si un experto opina, la muestra muta y solo pueden opinar expertos.
     * Entonces crea a la muestra se le cambia el estado.
     * 
     * @param op Una opinión que se agrega al diccionario de la nueva muestra 
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        MuestraExperto nuevoEstado = new MuestraExperto(this.muestra);
        nuevoEstado.agregarOpinionExperto(op);
        this.muestra.setEstado(nuevoEstado);
    }

    /**
     * Método para indicar que la muestra no está verificada
     * 
     * @return False porque la muestra sigue siendo votada 
     */
    @Override
    public boolean esVerificada() {
        return false;
    }
}
