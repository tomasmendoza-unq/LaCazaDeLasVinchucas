package unq.integrador.impls;

import java.time.LocalDate;

import unq.integrador.enums.TipoOpinion;

/**
 * Clase que representa las opiniones de los usuarios en muestras
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Opinion {
    private TipoOpinion tipo;
    private LocalDate fechaDeCreacion;

    /**
     * Constructor de las opiniones
     * @param tipo Que indica el tipo de voto que es del enum TipoOpinion
     */
    public Opinion(TipoOpinion tipo) {
        this.tipo = tipo;
        this.fechaDeCreacion = LocalDate.now();
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
     * Getter de la fecha de creación de la opinión
     * 
     * @return Fecha de creación
     */
    public LocalDate getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }
    
    /**
     * Método para imprimir el tipo de voto en la opinión
     * 
     * @return Un String que representa el tipo de voto
     */
    public String imprimirTipo() {
        return tipo.imprimirTipo();
    }
}
