package unq.integrador;

import java.util.ArrayList;
import java.util.HashMap;

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
    public MuestraLibre(Usuario user, String fotografia, String ubicacion) {
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
    public Opinion resultadoActual() {

        HashMap<Opinion, Integer> opMap = new HashMap<Opinion, Integer>();

        int maxNum = 0;
        Opinion maxOp = null;
        for (Opinion op : opiniones) {
            opMap.put(op, opMap.getOrDefault(op, 0) + 1);
            if (maxNum < opMap.get(op)) {
                maxNum = opMap.get(op);
                maxOp = op;
            }
        }

        return maxOp;
        // return (maxOp != null) ? maxOp : "No definido"; | quizás tendría que hacer un switch case
    }
    
    /**
     * Agregua una opinión a la lista en caso de que sea usuario normal, si es experto
     * la clase muta a MuestraExperto y agrega la opinión dada a una lista vacía en esa clase
     * 
     * @param op una opinión para agregar a la lista
     * @param user usuario que agrega la opinión
     */
    @Override
    public void addOpinion(Opinion op, Usuario user) {
        if(!user.isExperto()) {
            opiniones.add(op);
        } else {
            ArrayList<Opinion> opinionesExpertas = new ArrayList<Opinion>();
            opinionesExpertas.add(op);
            
            this.user.setMuestraPublicada(
                new MuestraExperto(
                    this.user, 
                    this.fotografia, 
                    this.ubicacion, 
                    opinionesExpertas)
                );
        }
    }
}
