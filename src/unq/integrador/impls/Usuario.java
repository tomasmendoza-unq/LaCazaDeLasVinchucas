package unq.integrador.impls;

import unq.integrador.*;
import unq.integrador.enums.Lapso;
import unq.integrador.enums.TipoOpinion;

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
	public Usuario(int id, IUsuarioRango rango, IBaseDeMuestras bdm) {
		this.id 		   = id;
		this.rango 		   = rango;
		this.bdm 		   = bdm;
		this.opinionList   = new ArrayList<Opinion>();
		this.publicaciones = new ArrayList<IMuestra>();
	}

	/** Getter del id de usuario
	 * 
	 * @return un int que representa el id
	 */
	public int getId() {
		return this.id;
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
	 * @param tipoOpinion TipoOpinion que será agregada a la muestra
	 */
	public void opinarSobreUnaMuestra(IMuestra muestra, TipoOpinion tipoOpinion) {
		Opinion opinion = new Opinion(this.id, tipoOpinion);
		this.rango.opinarSobreUnaMuestra(muestra,opinion);
		this.opinionList.add(opinion);
    }

	/**
	 * Método para crear y enviar una muestra a la base de muestras
	 * 
	 * @param fotografia Representa la foto que se subió como muestra
	 * @param ubicacion Representa la ubicación en la que se mandó la muestra
	 */
	public void enviarMuestra(String fotografia, IUbicacion ubicacion) {
		IMuestra muestra = new Muestra(this.id, fotografia, ubicacion, bdm);
		this.bdm.cargarMuestra(muestra);
		this.publicaciones.add(muestra);
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
	 * Método para quitar una muestra de la lista de publicaciones
	 *
	 * @param muestra que representa la muestra a quitar
	 */
	@Override
	public void quitarMuestra(IMuestra muestra) {
		this.publicaciones.remove(muestra);
	}

	/*
	* 	Metodo para verificar si publico una muestra dada
	*
	* @param muestra que representa la muestra a verificar
	* */
	@Override
	public boolean publicoEstaMuestra(IMuestra muestra){
		return publicaciones.contains(muestra);
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
	
	/* 
	 * 
	 * MÉTODOS SOBRE FILTROS
	 * 
	 */

	/**
	 * Método para conseguir una lista de muestras que concuerden con el filtro pasado
	 * 
	 * @param filtro Un filtro de muestras para aplicar
	 * @return Una lista de IMuestra que cumplen el filtro
	 */
	public List<IMuestra> buscarMuestra(FiltroMuestras filtro) {
		return bdm.filtrarMuestras(filtro);
	}

	/**
	 * Método para crear un filtro indicando el nivel de verificación que debería tener la muestra
	 * 
	 * @param nivel Un booleano que indica si se quiere que la muestra esté verificada o no
	 * @return Un filtro con el criterio dado
	 */
	public FiltroMuestras crearFiltroParaNivelDeVerificacion(boolean nivel) {
		return nivel 
		? new FiltroMuestras(f -> f.esVerificada()) // Para muestras verificadas
		: new FiltroMuestras(f -> !f.esVerificada()); // Para muestras votadas
	}

	/**
	 * Método para crear un filtro indicando desde una fecha que muestras se quieren.
	 * Si las que se crearon después, antes o ese mismo día
	 * 
	 * @param lapso Un lapso que indica a que momento corresponde la búsqueda
	 * @param fecha Una fecha que indica desde donde se tiene en cuenta el filtro 
	 * @return Un filtro con el criterio dado
	 */
	public FiltroMuestras crearFiltroParaFechaDeCreacion(Lapso lapso, LocalDate fecha) {
		switch (lapso) {
			case DESPUES:
				return new FiltroMuestras(f -> f.getFechaCreacion().isAfter(fecha));
			case ANTES:
				return new FiltroMuestras(f -> f.getFechaCreacion().isBefore(fecha));
			default:
				return new FiltroMuestras(f -> f.getFechaCreacion().equals(fecha));
		}
	}

	/**
	 * Método para crear un filtro indicando desde una fecha que muestras se 
	 * quieren en base a la última votación que recibieron e indicando si
	 * se crearon después, antes o ese mismo día.
	 * 
	 * @param lapso Un lapso que indica a que momento corresponde la búsqueda
	 * @param fecha Una fecha que indica desde donde se tiene en cuenta el filtro 
	 * @return Un filtro con el criterio dado
	 */
	public FiltroMuestras crearFiltroParaFechaDeUltimaVotacion(Lapso lapso, LocalDate fecha) {
		switch (lapso) {
			case DESPUES:
				return new FiltroMuestras(f -> f.getFechaUltimaVotacion().isAfter(fecha));
			case ANTES:
				return new FiltroMuestras(f -> f.getFechaUltimaVotacion().isBefore(fecha));
			default:
				return new FiltroMuestras(f -> f.getFechaUltimaVotacion().equals(fecha));
		}
	}

	/**
	 * Método para crear un filtro basado en el insecto que se registra en la muestra
	 * 
	 * @param tipo Un tipo de opinión que se pueda llegar a dar en la muestra.
	 * @return Un filtro con el criterio dado
	 */
	public FiltroMuestras crearFiltroParaInsectoDetectado(TipoOpinion tipo) {
		return new FiltroMuestras(
			f -> f.resultadoActual().equalsIgnoreCase(tipo.imprimirTipo())
			);
	}
}
