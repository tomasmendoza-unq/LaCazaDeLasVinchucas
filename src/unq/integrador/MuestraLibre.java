package unq.integrador;

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
            for (Opinion op : this.opiniones.keySet()) {
                if (maxNum < this.opiniones.get(op)) {
                    maxNum = this.opiniones.get(op);
                    maxOp = op;
                }
            }
        }

        // Si existiera un empate o el diccionario está vacío, retorna "No definido"
        // Caso contrario, dependiendo de la Opinión en maxOp, retorna cualquiera del switch
        return (maxOp == null) ? "No definido" : switch (maxOp) {
            case VINCHUCA_GUASAYANA -> "Vinchuca Guasayana";
            case VINCHUCA_INFESTANS -> "Vinchuca Infestans";
            case VINCHUCA_SORDIDA   -> "Vinchuca Sordida";
            case CHINCHA_FOLIADA    -> "Chincha Foliada";
            case PHTIA_CHINCHE      -> "Phtia Chinche";
            case NINGUNA            -> "Ninguna";
            case IMAGEN_POCO_CLARA  -> "Imagen poco clara";
        };
    }
    
    /**
     * Agregua una opinión a la lista en caso de que sea usuario normal, si es experto
     * la clase muta a MuestraExperto y agrega la opinión dada a una lista vacía en esa clase
     * 
     * @param op una opinión para agregar a la lista
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        this.opiniones.put(op, this.opiniones.getOrDefault(op, 0) + 1);
    }

    @Override
    public void agregarOpinionExperto(Opinion op) {
        MuestraExperto muestra = new MuestraExperto(user, fotografia, ubicacion);
        muestra.agregarOpinionExperto(op);
        this.user.setMuestraPublicada(muestra);
    }
}
