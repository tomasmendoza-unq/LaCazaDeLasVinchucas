package unq.integrador.Impls;

import unq.integrador.Enums.TipoOpinion;

import java.time.LocalDate;

public class Opinion {
    private LocalDate localDate;
    private TipoOpinion tipoOpinion;

    public Opinion(LocalDate localDate, TipoOpinion tipoOpinion) {
        this.localDate = localDate;
        this.tipoOpinion = tipoOpinion;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
