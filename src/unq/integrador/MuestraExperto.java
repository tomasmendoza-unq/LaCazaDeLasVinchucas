package unq.integrador;

import java.util.HashMap;

/**
 * Esta clase representa las muestras en las que solo pueden opinar expertos.
 * Además, si opinaron expertos en esta clase, ninguno tiene más de 1 coincidencia en la
 * misma opinión.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraExperto extends Muestra {

    /**
     * Constructor de la clase MuestraExperto
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     * @param opiniones un diccionario (Opinion, Integer) para las opiniones de usuarios, 
     * donde opinion es la clave y el Integer representa la cantidad de votos que tiene dicha opinion
     */
    public MuestraExperto(IUsuario user, String fotografia, String ubicacion, HashMap<Opinion, Integer> opiniones) {
        super(user, fotografia, ubicacion);
        this.opiniones = opiniones;
    }

    /**
     * Método para conseguir la opinión con más votos
     *
     * @return retorna el resultado actual que sería la clave del diccionario con el valor más alto
     */
    @Override
    public String resultadoActual() {
        
        if(this.opiniones.values().size() > 1) {
            return "No definido";
        } else {
            Opinion resultado = null;
            for (Opinion op : this.opiniones.keySet()) {
                resultado = op;
            }

            switch (resultado) {
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
                    return "No definida";
            }
        }
    }

    /**
     * Agregua una opinión a la lista en caso de que sea usuario experto,
     * si fuera usuario básico tiraría una excepción.
     * Si alguna opinión tiene 2 votos, significa que la votaron 2 expertos
     * por ende, la muestra queda verificada y cambia por MuestraVerificada.
     * 
     * @param op una opinión para agregar a la lista
     * @param esExperto condición para agregar al diccionario de opiniones
     */
    @Override
    public void agregarOpinion(Opinion op, boolean esExperto) {
        if (esExperto) {
            this.opiniones.put(op, this.opiniones.getOrDefault(op, 0) + 1);
            
            if (this.opiniones.get(op) == 2) {
                this.cerrarMuestraCon(op);
            }
        } /*else {
            // lanzar excepción
        }*/
    }

    /**
     * Método para cambiar la muestra del usuario por otra versión.
     * En este caso cambia a MuestraVerificada.
     * 
     * @param op opinión que sería el resultado de todas las opiniones
     */
    private void cerrarMuestraCon(Opinion op) {
        this.user.setMuestraPublicada(
            new MuestraVerificada(
                this.user, 
                this.fotografia, 
                this.ubicacion,
                op)
            );
    }

}
