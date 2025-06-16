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
    private String categoria;
    
    /**
     * Constructor de las opiniones
     * @param tipo Que indica el tipo de voto que es del enum TipoOpinion
     */
    public Opinion(TipoOpinion tipo) {
        this.tipo = tipo;
        this.fechaDeCreacion = LocalDate.now();
        this.idUsuario = -1;
        this.categoria = null;
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

    /**
     * Setter de id para cambiar el valor por default
     * 
     * @param id Representa el id del usuario
     */
    public void setID(int id) {
        this.idUsuario = id;
    }
    
    /**
     * Getter de la categoría de quien proporciona la opinión
     * 
     * @return Categoría del usuario que publicó una opinión
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * Setter de la categoría del usuario en la opinión para cambiar el valor por default
     * 
     * @param categoria Representa la categoría del usuario que publicó
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
