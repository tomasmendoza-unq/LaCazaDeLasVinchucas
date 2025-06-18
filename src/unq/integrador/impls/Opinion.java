package unq.integrador.impls;

import java.time.LocalDate;

import unq.integrador.enums.TipoOpinion;

/**
 * Clase que representa las opiniones de los usuarios en muestras
 * 
 @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Opinion {
    private TipoOpinion tipo;
    private LocalDate fechaDeCreacion;
    private int idUsuario;
    /**
     * Constructor de las opiniones
     * @param tipo Que indica el tipo de voto que es del enum TipoOpinion
     */
    public Opinion(TipoOpinion tipo, int idUsuario) {
        this.tipo = tipo;
        this.fechaDeCreacion = LocalDate.now();
        this.idUsuario = idUsuario;
    }

    /**
     * Getter del tipo de opinion
     * 
     * @return tipo de opinion del enum
     */
    public TipoOpinion getTipo() {
        return this.tipo;
    }
    
    /**
     * Método para imprimir el tipo de voto en la opinión
     * 
     * @return Un String que representa el tipo de voto
     */
    public String imprimirTipo() {
        return tipo.imprimirTipo();
    }

    /**
     * Getter de la fecha de creación de la opinión
     * 
     * @return Fecha de creación
     */
    public LocalDate getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }

    /**
     * Getter del ID del usuario que publicó la opinión
     *
     * @return El ID de un usuario que publicó una opinión concreta
     */
    public int getID() {
        return this.idUsuario;
    }




}
