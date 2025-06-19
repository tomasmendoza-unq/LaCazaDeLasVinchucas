package unq.integrador;

import java.time.LocalDate;

import unq.integrador.impls.Opinion;

/**
 * Interfaz de Muestra
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IMuestra {
    public String resultadoActual();
    public void agregarOpinionBasico(Opinion op);
    public void agregarOpinionExperto(Opinion op);
    public String getFotografia();
    public IUbicacion getUbicacion();
    public void cargarMuestraVerificada();
    public int getIDUsuario();
    public LocalDate getFechaCreacion();
    public void agregarAlHistorial(Opinion op, String categoria);
    public String verRegistroN(int n);
    public void setEstado(IEstadoDeMuestra estado);
    public boolean esVerificada();
    public LocalDate getFechaUltimaVotacion();
}
