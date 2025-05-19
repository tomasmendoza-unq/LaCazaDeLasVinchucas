package unq.integrador;

/**
 * Interfaz de Muestra
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IMuestra {
    public Opinion resultadoActual();
    public void addOpinion(Opinion op, Usuario user);
}
