package unq.integrador;

import java.time.LocalDate;

import unq.integrador.impls.Opinion;

/**
 * Interfaz de Muestra
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IMuestra {
    public int getIDUsuario();
    public String getFotografia();
    public IUbicacion getUbicacion();
    public LocalDate getFechaCreacion();
    public LocalDate getFechaUltimaVotacion();
    public void setEstado(IEstadoDeMuestra estado);
    public String resultadoActual();
    public void agregarOpinionBasico(Opinion op);
    public void agregarOpinionExperto(Opinion op);
    public void agregarAlHistorial(Opinion op, String categoria);
    public String verRegistroNro(int n);
    public boolean esVerificada();
    public void cargarMuestraVerificada();
}
