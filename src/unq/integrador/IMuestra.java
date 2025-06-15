package unq.integrador;

import unq.integrador.Impls.Opinion;

import java.time.LocalDate;

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
    public LocalDate getFechaCreacion();
}
