package unq.integrador;

import java.util.HashMap;

/**
 * Esta es la clase abstracta de Muestra, desde esta heredan
 * MuestraLibre, MuestraExperto y MuestraVerificada.
 * Conoce a su usuario para poder pasar de tipo de muestra
 * La fotografía es un String porque no encontré mejor representación
 * La ubicación es un String MOMENTÁNEAMENTE porque la implementación de Ubicación todavía no es mi sección
 * También tiene una HashMap de opiniones (Un Enum), que contiene todas las opiniones de usuarios como claves
 * y como valores la cantidad de opiniones de esa clave.

 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public abstract class Muestra implements IMuestra {
    protected IUsuario user;
    protected String fotografia;
    protected String ubicacion;
    protected HashMap<Opinion, Integer> opiniones;
    
    /**
     * Constructor de la clase Abstracta Muestra
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     */
    public Muestra(IUsuario user, String fotografia, String ubicacion) {
        this.user = user;
        this.fotografia = fotografia;
        this.ubicacion = ubicacion;
        this.opiniones = new HashMap<Opinion, Integer>();
    }

    /**
     * Método para conseguir el resultado actual de opiniones,
     * en esta clase es abstracto porque cada tipo de muestra lo calcula distinto
     * 
     * @return La opinión con más votos, que sería la que más
     * ocurrencias tiene en la lista de opiniones de la clase
     */
    public abstract String resultadoActual();

    /**
     * Agregua una opinión al diccionario.
     * 
     * @param op una opinión para agregar a la lista
     */
    public abstract void agregarOpinion(Opinion op, boolean esExperto);
}
