package unq.integrador.impls.Filtros;

import unq.integrador.enums.Lapso;

import java.time.LocalDate;

public abstract class FiltroPorFecha extends FiltroBasico{
    protected Lapso lapso;
    protected LocalDate fecha;

    public FiltroPorFecha(Lapso lapso, LocalDate fecha) {
        this.lapso = lapso;
        this.fecha = fecha;
    }
}
