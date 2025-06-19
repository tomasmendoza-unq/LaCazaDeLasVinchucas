package unq.integrador;

import unq.integrador.impls.MuestraLibre;
import unq.integrador.impls.Opinion;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa las muestras de insectos del sistema.
 * 
 * Estas muestras conocen el ID de su usuario, la fotografía del insecto,
 * la ubicación en la que fue tomada la muestra. También recuerdan la fecha en la
 * que fueron creadas, las personas que opinaron sobre la muestra y tiene un estado
 * que va cambiando dependiendo quien opine.
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario.
 */
public class Muestra implements IMuestra {
    private int userID;
    private String fotografia;
    private IUbicacion ubicacion;
    private ArrayList<String> historial;
    private LocalDate fechaCreacion;
    private IEstadoDeMuestra estado;
    LocalDate fechaUltimaVotacion;
    
    /**
     * Constructor de la clase Abstracta Muestra
     * @param id Representa el ID del usuario que publicó la muestra
     * @param fotografia Representa la fotografía del usuario que publicó la muestra
     * @param ubicacion Representa la ubicación de donde se publicó la muestra
     */
    public Muestra(int id, String fotografia, IUbicacion ubicacion) {
        this.userID         = id;
        this.fotografia     = fotografia;
        this.ubicacion      = ubicacion;
        this.fechaCreacion  = LocalDate.now();
        this.historial      = new ArrayList<String>();
        this.estado         = new MuestraLibre(this);
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
     */
    public void agregarOpinionBasico(Opinion op) {
        this.estado.agregarOpinionBasico(op);
        this.agregarAlHistorial(op, "Básico");
        this.fechaUltimaVotacion = op.getFechaDeCreacion();
    }
    
    /**
     * Agregua una opinión de un usuario experto a la muestra.
     * Además agrega esa interación en el historial.
     * 
     * @param op una opinión para agregar a la muestra
     * @see IEstadoDeMuestra
     */
    public void agregarOpinionExperto(Opinion op) {
        this.estado.agregarOpinionExperto(op);
        this.agregarAlHistorial(op, "Experto");
        this.fechaUltimaVotacion = op.getFechaDeCreacion();
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
     * Getter del ID del usuario que publicó la muestra
     * 
     * @return ID del usuario
     */
    public int getIDUsuario() {
        return this.userID;
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
     * Método para agregar un registro al historial de opiniones realizadas en la muestra
     * 
     * @param op Opinión que se añade a la muestra
     * @param categoria Categoría a la que pertenece el usuario que opinó
     */
    public void agregarAlHistorial(Opinion op, String categoria) {
        String log = 
            "Usuario " + op.getID() +
            " opinó: " + op.imprimirTipo() +
            ", en la fecha: " + op.getFechaDeCreacion().toString() +
            ", con categoría: " + categoria;
        
        this.historial.add(log);
    }

    /**
     * Método para obtener algún registro del historial
     * 
     * @param n Representa una posición posible entre los registros del historial
     * @return Un String que contiene quien votó, cuando, su categoría y cual fue su voto
     */
    public String verRegistroN(int n) {
        return this.historial.get(n-1);
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
     * Getter de la fecha de la última votación que se realizó en la muestra
     * 
     * @return LocalDate con la fecha de la última votación
     */
    @Override
    public LocalDate getFechaUltimaVotacion() {
        return this.fechaUltimaVotacion;
    }  
}
