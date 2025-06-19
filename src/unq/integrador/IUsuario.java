package unq.integrador;

import java.time.LocalDate;
import java.util.List;

import unq.integrador.enums.Lapso;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.FiltroMuestras;
import unq.integrador.impls.Opinion;
/**
 * Interfaz de Usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IUsuario {
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) ;
    public void enviarMuestra(String fotografia, IUbicacion ubicacion);
    public void determinarRango();
    public boolean subeDeRango();
    public void quitarMuestra(IMuestra muestra);
    public void agregarMuestraPublicada(IMuestra muestra);
    public int getID();
    public boolean publicoEstaMuestra(IMuestra muestra);
    public List<IMuestra> buscarMuestra(FiltroMuestras filtro);
    public FiltroMuestras crearFiltroParaNivelDeVerificacion(boolean nivel);
    public FiltroMuestras crearFiltroParaFechaDeCreacion(Lapso op, LocalDate fecha);
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacion(Lapso op, LocalDate fecha);
    public FiltroMuestras crearFiltroParaInsectoDetectado(TipoOpinion tipo);
}
