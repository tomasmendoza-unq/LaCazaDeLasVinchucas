package unq.integrador;

import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;
import unq.integrador.impls.UsuarioRango;

/**
 * Interfaz de Usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IUsuario {
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
    public void agregarMuestraPublicada(IMuestra muestra);
    public void determinarRango();
    public boolean subeDeRango();
    public void setProximoRango(UsuarioRango usuarioRango);
    // public List<IMuestra> buscarMuestra(FiltroMuestras filtro);
    // public FiltroMuestras crearFiltroParaNivelDeVerificacion(boolean nivel);
    // public FiltroMuestras crearFiltroParaFechaDeCreacion(Lapso op, LocalDate fecha);
    // public FiltroMuestras crearFiltroParaFechaDeUltimaVotacion(Lapso op, LocalDate fecha);
    // public FiltroMuestras crearFiltroParaInsectoDetectado(TipoOpinion tipo);
}
