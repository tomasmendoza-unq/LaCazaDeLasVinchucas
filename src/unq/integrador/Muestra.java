package unq.integrador;

import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;

import java.time.LocalDate;
import java.util.ArrayList;
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
    protected IUbicacion ubicacion;
    protected HashMap<TipoOpinion, Integer> opiniones;
    protected ArrayList<String> historial;
    protected LocalDate fechaCreacion;
    
    /**
     * Constructor de la clase Abstracta Muestra
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     */
    public Muestra(IUsuario user, String fotografia, IUbicacion ubicacion) {
        this.user = user;
        this.fotografia = fotografia;
        this.ubicacion = ubicacion;
        this.opiniones = new HashMap<TipoOpinion, Integer>();
        this.historial = new ArrayList<String>();
        this.fechaCreacion = LocalDate.now();
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
     * Agregua una opinión de un usuario básico al diccionario.
     * 
     * @param op una opinión para agregar a la lista
     */
    public abstract void agregarOpinionBasico(Opinion op);
    
    /**
     * Agregua una opinión de un usuario experto al diccionario.
     * 
     * @param op una opinión para agregar a la lista
     */
    public abstract void agregarOpinionExperto(Opinion op);

    /**
     * Getter de la fotografía de la muestra
     * 
     * @return Un string que representa la fotografía
     */
    public String getFotografia() {
        return this.fotografia;
    }

    /**
     * Getter de la ubicación de la muestra
     * 
     * @return Ubicación de la muestra
     */
    public IUbicacion getUbicacion() {
        return this.ubicacion;
    }

    /**
     * Getter del ID del usuario que publicó la muestra
     * 
     * @return ID del usuario
     */
    public int getIDUsuario() {
        return this.user.getID();
    }
    
    /**
     * Getter de la fecha de creación de la muestra
     * 
     * @return la fecha de creación de la muestra
     */
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Método para agregar un registro al historial de opiniones realizadas en la muestra
     * 
     * @param op Opinión que se añade a la muestra
     */
    public void agregarAlHistorial(Opinion op) {
        String log = 
            "Usuario " + op.getID() +
            " opinó: " + op.imprimirTipo() +
            ", en la fecha: " + op.getFechaDeCreacion().toString() +
            ", con categoría: " + op.getCategoria();
        this.historial.add(log);
    }

    /**
     * Método para obtener algún registro del historial
     * 
     * @param n Que representa una posición posible entre los registros del historial
     * @return Un registro que representa quien votó, cuando, su categoría y cual fue su voto
     */
    public String verRegistroN(int n) {
        return this.historial.get(n-1);
    }
}
