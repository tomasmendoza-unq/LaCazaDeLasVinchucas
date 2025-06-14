package unq.integrador;

/**
 * Interfaz de Muestra
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IMuestra {
    public String resultadoActual();
    public void agregarOpinionBasico(Opinion op);
    public void agregarOpinionExperto(Opinion op);
    public String getFotografia();
    public String getUbicacion();
    public int getIDUsuario();
}
