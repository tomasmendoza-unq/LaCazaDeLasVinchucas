package unq.integrador;

import java.time.LocalDate;
import java.util.List;

import unq.integrador.enums.Lapso;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.FiltroMuestras;
import unq.integrador.impls.Opinion;

/**
 * Interfaz de la base de muestras
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface ISistema {
	public void cargarMuestra(IMuestra muestra, IUsuario usuario);
    public void opinarSobre(IMuestra muestra, IUsuario usuario, Opinion opinion) throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
    public void notificarVerificacion(IMuestra muestra);
	public void RegistrarZona(IZonaDeCobertura zonaDeCobertura);
	public List<IMuestra> filtrarMuestras(FiltroMuestras filtro);
	
    public FiltroMuestras crearFiltroParaNivelDeVerificacion(boolean nivel);
    public FiltroMuestras crearFiltroParaFechaDeCreacion(Lapso lapso, LocalDate fecha);
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacion(Lapso lapso, LocalDate fecha);
    public FiltroMuestras crearFiltroParaInsectoDetectado(TipoOpinion tipo);
}
