package unq.integrador.impls.Muestras;

import unq.integrador.*;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa las muestras de insectos del sistema.
 * 
 * Estas muestras conocen el usuario que publico la muestra, la fotografía del insecto,
 * la ubicación en la que fue tomada la muestra. También recuerdan la fecha en la
 * que fueron creadas, las personas que opinaron sobre la muestra y tiene un estado
 * que va cambiando dependiendo quien opine.
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario.
 */
public class Muestra implements IMuestra {
    private IUsuario owner;
    private String fotografia;
    private IUbicacion ubicacion;
    private ISistema sistema;
    private LocalDate fechaCreacion;
    private Map<IUsuario, Opinion> historial;
    private IEstadoDeMuestra estado;
    private LocalDate fechaUltimaVotacion;
    
    /**
     * Constructor de la clase Abstracta Muestra
     * @param owner Representa al usuario que publico la muestra
     * @param fotografia Representa la fotografía del usuario que publicó la muestra
     * @param ubicacion Representa la ubicación de donde se publicó la muestra
     * @param sistema Una base de muestras donde cargarse
     */
    public Muestra(IUsuario owner,String fotografia, IUbicacion ubicacion, ISistema sistema) {
        this.owner         = owner;
        this.fotografia    = fotografia;
        this.ubicacion     = ubicacion;
        this.sistema = sistema;
        this.fechaCreacion = LocalDate.now();
        this.historial     = new HashMap<>();
        this.estado        = new MuestraLibre(this);
    }

    /**
     * Getter de la fotografía de la muestra
     * 
     * @return Un string que representa la fotografía
     */
    public String getFotografia() {
        return this.fotografia;
    }

    /**
     * Getter de la ubicación de la muestra
     * 
     * @return Ubicación de la muestra
     */
    public IUbicacion getUbicacion() {
        return this.ubicacion;
    }

    /**
     * Getter de la fecha de creación de la muestra
     * 
     * @return la fecha de creación de la muestra
     */
    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    /**
     * Getter de la fecha de creación de la muestra
     *
     * @return la fecha de creación de la muestra
     */
    public IUsuario getOwner() {
        return owner;
    }

    /**
     * Getter del historial de la muestra
     *
     * @return el historial de la muestra
     */
    public Map<IUsuario, Opinion> getHistorial() {
        return historial;
    }


    /**
     * Getter de la fecha de la última votación que se realizó en la muestra
     * 
     * @return LocalDate con la fecha de la última votación
     */
    @Override
    public LocalDate getFechaUltimaVotacion() {
        return this.fechaUltimaVotacion;
    }
    
    /**
     * Setter del estado de la muestra
     * 
     * @param estado Un estado nuevo por el que cambiar el anterior
     */
    public void setEstado(IEstadoDeMuestra estado) {
        this.estado = estado;
    }

    /**
     * Método para conseguir el resultado actual de opiniones
     * 
     * @see IEstadoDeMuestra
     * @return La opinión con más votos, que sería la que más
     * ocurrencias tiene entre los votos del resto de usuarios
     */
    public String resultadoActual() {
        return this.estado.resultadoActual();
    }

    /**
     * Agregua una opinión de un usuario básico a la muestra.
     * Además agrega esa interación en el historial.
     * 
     * @param op una opinión para agregar a la muestra
     * @see IEstadoDeMuestra
     * @throws SinAccesoAMuestraException Dependiendo del estado de la muestra y el rango del usuario que llame este método 
     * @throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException Si un usuario opina por segunda vez
     */
    public void agregarOpinionBasico(IUsuario usuario, Opinion op) throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException {
        this.revisarOpinion(usuario);
        this.estado.agregarOpinionBasico(op);
        this.agregarAlHistorial(usuario,op);
        this.fechaUltimaVotacion = op.getFechaDeCreacion();
    }

    /**
     * Agregua una opinión de un usuario experto a la muestra.
     * Además agrega esa interación en el historial.
     * 
     * @param op una opinión para agregar a la muestra
     * @throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException Si un usuario opina por segunda vez
     * @throws SinAccesoAMuestraException Dependiendo del estado de la muestra y el rango del usuario que llame este método 
     * @see IEstadoDeMuestra
     */
    public void agregarOpinionExperto(IUsuario usuario, Opinion op) throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException {
        this.revisarOpinion(usuario);
        this.estado.agregarOpinionExperto(op);
        this.agregarAlHistorial(usuario,op);
        this.fechaUltimaVotacion = op.getFechaDeCreacion();
    }

    /**
     * Método para indicar si el usuario ya votó en la muestra
     * o es el mismo dueño quien quiere opinar
     * 
     * @param
     * @throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException
     */
    private void revisarOpinion(IUsuario usuario) throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException {
        // Si el owner coincide con el usuario
        if (owner.equals(usuario)){
            throw new UnUsuarioNoPuedeOpinarEnSuMuestraException("Un usuario no puede opinar sobre una muestra publicada por él mismo");
        }

        // Si el usuario pertenece al historial
        if (historial.containsKey(usuario)) {
            throw new UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException("Un usuario no puede opinar dos veces en una muestra");
        }
    }

    /**
     * Método para agregar un registro al historial de opiniones realizadas en la muestra
     *
     * @param usuario   Usuario que realizo la opinion
     * @param op        Opinión que se añade a la muestra
     */
    private void agregarAlHistorial(IUsuario usuario, Opinion op) {
        historial.put(usuario, op);
    }


    /**
     * Método para indicar si la muestra está verificada
     * 
     * @return Un bool indicando si está verificada la muestra
     * @see IEstadoDeMuestra
     */
    @Override
    public boolean esVerificada() {
        return estado.esVerificada();
    }

    /**
     * Método que avisa a la base de muestras que esta muestra quedó verificada
     * 
     */
    @Override
    public void cargarMuestraVerificada() {
        this.sistema.notificarVerificacion(this);
    }
}
