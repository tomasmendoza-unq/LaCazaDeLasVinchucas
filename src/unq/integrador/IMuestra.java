package unq.integrador;

import java.time.LocalDate;
import java.util.Map;

import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;

/**
 * Interfaz de Muestra
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IMuestra {
    public String getFotografia();
    public IUbicacion getUbicacion();
    public LocalDate getFechaCreacion();
    public LocalDate getFechaUltimaVotacion();
    public Map<IUsuario, Opinion> getHistorial();
    public IUsuario getOwner();
    public void setEstado(IEstadoDeMuestra estado);
    public String resultadoActual();
    public void agregarOpinionBasico(IUsuario usuario, Opinion op) throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException;
    public void agregarOpinionExperto(IUsuario usuario, Opinion op) throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException;
    public boolean esVerificada();
    public void cargarMuestraVerificada();
}
