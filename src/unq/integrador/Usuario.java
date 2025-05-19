package unq.integrador;

/**
 * Clase abstracta de Usuario, desde esta clase heredan
 * UsuarioExperto y UsuarioBasico.
 * 
 * Todos los usuarios tiene un nombre, un id, todos tienen una muestra
 * y un booleano para indicar si son expertos
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public abstract class Usuario {
    protected String nombre;
    protected int id;
    protected IMuestra miMuestra;

    /**
     * Constructor de Usuario
     * @param nombre nombre de usuario
     * @param id número de identificación del usuario
     */
    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    /**
     * Método para publicar una muestra, inicia sin opiniones
     * @param fotografia Una fotografía de la plaga
     * @param ubicacion La ubicación de la muestra
     */
    public void publicarMuestra(String fotografia, String ubicacion) {
        this.miMuestra = new MuestraLibre(this, fotografia, ubicacion);
    }

    /**
     * Método para agregar la opinión a la muestra dada por parámetro
     * @param muestra Una muestra de la que opinar
     * @param op La opinión misma que se agrega a la muestra
     */
    public void opinarDeMuestra(IMuestra muestra, Opinion op) {
        muestra.addOpinion(op, this);
    }

    /**
     * Setter de miMuestra, que cambia el estado de la muestra a una donde solo opinan expertos
     * @param muestra Muestra con al menos una opinión de un experto
     */
    protected void setMuestraPublicada(IMuestra muestra) {
        miMuestra = muestra;
    }

    /**
     * Método para saber si el usuario es experto
     * @return Si el usuario es experto o no
     */
    public abstract boolean isExperto();
}
