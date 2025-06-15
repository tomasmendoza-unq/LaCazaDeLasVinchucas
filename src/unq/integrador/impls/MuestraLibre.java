package unq.integrador.impls;

import unq.integrador.*;
import unq.integrador.enums.TipoOpinion;

/**
 * Esta clase representa las muestras libres, que son aquellas en
 * las que todos los usuarios pueden participar o, véase, no participó
 * ningún Experto todavía.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */

public class MuestraLibre extends Muestra {

    /**
     * Constructor de la clase MuestraLibre
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     */
    public MuestraLibre(IUsuario user, String fotografia, String ubicacion) {
        super(user, fotografia, ubicacion);
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
        int maxNum = 0;
        TipoOpinion maxOp = null;

        // Se comprueva si no existen dos opiniones con la misma cantidad
        Integer max1 = null;
        Integer max2 = null;

        for (Integer n : this.opiniones.values()) {
            if (max1 == null || n > max1) {
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max2 = n;
            }
        }

        // Se consigue la opinión que tenga la ocurrencia más alta
        // Si no existen dos opiniones con la misma cantidad.
        if (max1 != max2) {
            for (TipoOpinion op : this.opiniones.keySet()) {
                if (maxNum < this.opiniones.get(op)) {
                    maxNum = this.opiniones.get(op);
                    maxOp = op;
                }
            }
        }

        // Si existiera un empate o el diccionario está vacío, retorna "No definido"
        // Caso contrario, dependiendo de la Opinión en maxOp, retorna cualquiera del switch
        return (maxOp == null) ? "No definido" : maxOp.imprimirTipo();
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
        this.agregarAlHistorial(op);
    }

    /**
     * Crea una nueva muestra que es para expertos y agrega la opinión que llegó
     * Además, cambia su instancia en el usuario para que tenga la muestra actualizada.
     * 
     * @param op Una opinión que se agrega al diccionario de la nueva Muestra 
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        MuestraExperto muestra = new MuestraExperto(this.user, this.fotografia, this.ubicacion, this.historial);
        muestra.agregarOpinionExperto(op);
        this.user.setMuestraPublicada(muestra);
    }
}
