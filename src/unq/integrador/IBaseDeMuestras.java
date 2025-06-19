package unq.integrador;

import java.util.List;

import unq.integrador.impls.FiltroMuestras;

/**
 * Interfaz de la base de muestras
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IBaseDeMuestras {
	public void cargarMuestra(IMuestra muestra) ;
	public void RegistrarZona(IZonaDeCobertura zonaDeCobertura);
    public void cargarMuestraVerificada(IMuestra muestra);
	public List<IMuestra> filtrarMuestras(FiltroMuestras filtro);
}
