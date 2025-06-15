package unq.integrador.impls;

import unq.integrador.*;

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

	private int id;
	private IBaseDeMuestras bdm;
	private IUsuarioRango rango;
	private List<Opinion> opinionList;
	private List<IMuestra> publicaciones;
	
	/**
	 * Constructor de Usuario
	 * 
	 * @param id Identificador del usuario
	 * @param bdm Bases de muestra para almacenar todas las muestras
	 * @param rango Categoría del usuario
	 */
	public Usuario(IBaseDeMuestras bdm, IUsuarioRango rango, int id) {
		this.id = id;
		this.bdm = bdm;
		this.rango = rango;
		this.opinionList = new ArrayList<Opinion>();
		this.publicaciones = new ArrayList<IMuestra>();
	}

	/**
	 * Método para opinar sobre una muestra dada
	 * 
	 * @param muestra Muestra dada sobre la que se agrega la opinión
	 * @param opinion Opinión que será agregada a la muestra
	 */
	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
		opinion.setID(this.id);
		this.rango.opinarSobreUnaMuestra(muestra,opinion);
		this.opinionList.add(opinion);
    }

	/**
	 * Método para crear y enviar una muestra libre a la base de muestras
	 * 
	 * @param fotografia Representa la foto que se subió como muestra
	 * @param ubicacion Representa la ubicación en la que se mandó la muestra
	 */
	public void enviarMuestra(String fotografia, String ubicacion) {
		MuestraLibre muestra = new MuestraLibre(this, fotografia, ubicacion);
		this.bdm.agregarMuestra(muestra);
		this.publicaciones.add(muestra);
	}

	/**
	 * Método para cambiar el rango del usuario dependiendo de si cumple
	 * las condiciones mínimas
	 */
	public void determinarRango(){
		this.rango = this.subeDeRango() ? this.setExperto() : this.setBasico();
	}

	/**
	 * Devuelve un usuario de categoría básica
	 * @return Un usuario de categoría básica
	 */
	private IUsuarioRango setBasico() {
		return new UsuarioBasico();
	}

	/**
	 * Devuelve un usuario de categoría experto
	 * @return Un usuario de categoría experto
	 */
	private IUsuarioRango setExperto() {
		return new UsuarioExperto();
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
	 * Método para quitar una muestra de la lista de publicaciones
	 *
	 * @param muestra que representa la muestra a quitar
	 */
	@Override
	public void quitarMuestra(IMuestra muestra) {
		this.publicaciones.remove(muestra);
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

	/**
	 * Getter de id del usuario
	 * 
	 * @return ID del usuario
	 */
	@Override
	public int getID() {
		return this.id;
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
