package unq.integrador;

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
        Opinion maxOp = null;
        for (Opinion op : this.opiniones.keySet()) {
            if (maxNum < this.opiniones.get(op)) {
                maxNum = this.opiniones.get(op);
                maxOp = op;
            }
        }

        switch (maxOp) {
            case VINCHUCA_GUASAYANA:
                return "Vinchuca Guasayana";
            
            case VINCHUCA_INFESTANS:
                return "Vinchuca Infestans";
            
            case VINCHUCA_SORDIDA:
                return "Vinchuca Sordida";
            
            case CHINCHA_FOLIADA:
                return "Chincha Foliada";
            
            case PHTIA_CHINCHE:
                return "Phtia Chinche";
            
            case NINGUNA:
                return "Ninguna";
            
            case IMAGEN_POCO_CLARA:
                return "Imagen poco clara";
            
            default:
                return "No definido";
        }
        /**
         * Esto todavía no contempla empates
         */
    }
    
    /**
     * Agregua una opinión a la lista en caso de que sea usuario normal, si es experto
     * la clase muta a MuestraExperto y agrega la opinión dada a una lista vacía en esa clase
     * 
     * @param op una opinión para agregar a la lista
     */
    @Override
    public void agregarOpinion(Opinion op, boolean esExperto) {
        if (!esExperto) {
            this.opiniones.put(op, this.opiniones.getOrDefault(op, 0) + 1);
        } else {
            this.cerrarMuestraCon(op);
        }
    }

    /**
     * Método para cambiar la muestra del usuario que la publicó por una
     * muestra en la que solo opinan expertos.
     * 
     * @param op Opinión que se agrega al diccionario de la muestra de expertos.
     */
    public void cerrarMuestraCon(Opinion op) {
        HashMap<Opinion, Integer> opiniones = new HashMap<Opinion, Integer>();
        
        opiniones.put(op, 1);

        this.user.setMuestraPublicada(
            new MuestraExperto(
                this.user, 
                this.fotografia, 
                this.ubicacion,
                opiniones)
            );
    }
}
