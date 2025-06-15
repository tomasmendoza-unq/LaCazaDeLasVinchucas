package unq.integrador.impls;

import java.time.LocalDate;

import unq.integrador.enums.TipoOpinion;

public class Opinion {
    private TipoOpinion tipo;
    private LocalDate fechaDeCreacion;
    
    public Opinion(TipoOpinion tipo) {
        this.tipo = tipo;
        this.fechaDeCreacion = LocalDate.now();
    }

    public TipoOpinion getTipo() {
        return this.tipo;
    }
    
    public String imprimirTipo() {
        return tipo.imprimirTipo();
    }

    public LocalDate getFechaDeCreacion() {
        return this.fechaDeCreacion;
    }
}
