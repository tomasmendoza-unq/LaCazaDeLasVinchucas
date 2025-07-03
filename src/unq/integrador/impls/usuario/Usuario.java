package unq.integrador.impls.usuario;

import unq.integrador.*;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a los usuarios del sistemas
 * los cuales interactúan creando u opinando sobre muestras
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Usuario implements IUsuario {

	private UsuarioRango rango;
	private List<Opinion> opinionList;
	private List<IMuestra> publicaciones;

	/**
	 * Constructor de Usuario
	 *
	 * @param rango Categoría del usuario
	 */
	public Usuario(UsuarioRango rango) {
		this.rango 		   = rango;
		this.opinionList   = new ArrayList<Opinion>();
		this.publicaciones = new ArrayList<IMuestra>();
	}
	/* 
	 * 
	 * MÉTODOS SOBRE MUESTRAS
	 * 
	 */

	/**
	 * Método para opinar sobre una muestra dada
	 * 
	 * @param muestra Muestra dada sobre la que se agrega la opinión
	 * @param opinion Opinion sobre una muestra
	 * @throws SinAccesoAMuestraException 
	 * @throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException 
	 * @throws UnUsuarioNoPuedeOpinarEnSuMuestraException 
	 */
	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
		this.rango.opinarSobreUnaMuestra(muestra,opinion, this);
		this.opinionList.add(opinion);
    }

	/**
	 * Método para agregar una muestra a la lista de publicaciones
	 *
	 * @param muestra que representa la muestra que se agrega
	 */
	@Override
	public void agregarMuestraPublicada(IMuestra muestra) {
		this.publicaciones.add(muestra);
	}

	/* 
	 * 
	 * MÉTODOS SOBRE RANGO DEL USUARIO
	 * 
	 */

	/**
	 * Método para cambiar el rango del usuario dependiendo de si cumple
	 * las condiciones mínimas
	 */
	public void determinarRango(){
		rango.determinarSiguienteRango(this);
	}

	/**
	 * Devuelve un usuario de categoría básica
	 * @param usuarioRango proximo rango de usuario
	 */
	public void setProximoRango(UsuarioRango usuarioRango) {
		this.rango = usuarioRango;
	}

	/**
	 * Método que indica si se cumple la condición
	 * para subir o bajar de rango. Donde se debe cumplir
	 * que las opiniones y publicaciones necesarias sean de cierta cantidad
	 * 
	 * @return Un booleano que indica si sube de rango
	 */
	public boolean subeDeRango() {
		return this.opinionesNecesarias() && this.publicacionesNecesarias();
	}

	/**
	 * Método que indica si el usuario tiene la cantidad de publicaciones
	 * hechas en los últimos 30 días
	 * 
	 * @return Booleano que indica si tiene 10 publicaciones en 30 días
	 */
	protected boolean publicacionesNecesarias() {
		return this.publicacionesDentroDeLos30Dias() >= 10;
	}

	/**
	 * Método que indica la cantidad de muestras que se publicaron
	 * en los últimos 30 días
	 * 
	 * @return la cantidad de muestras publicadas en 30 días
	 */
	protected long publicacionesDentroDeLos30Dias() {
		return 
			publicaciones.stream()
			.filter(muestra -> muestra.getFechaCreacion().isAfter(LocalDate.now().minusDays(30)))
			.count();
	}

	/**
	 * Método que indica si el usuario tiene la cantidad de opiniones
	 * hechas en los últimos 30 días
	 * 
	 * @return Booleano que indica si tiene 20 opiniones en 30 días
	 */
	protected boolean opinionesNecesarias() {
		return this.opinionesHechasDentroDeLos30Dias() >= 20;
	}

	/**
	 * Método que indica la cantidad de muestras que se publicaron
	 * en los últimos 30 días
	 * 
	 * @return la cantidad de muestras publicadas en 30 días
	 */
	protected long opinionesHechasDentroDeLos30Dias() {
		return
			opinionList.stream()
			.filter(op -> op.getFechaDeCreacion().isAfter(LocalDate.now().minusDays(30)))
			.count();
	}
}
