package unq.integrador.impls.Filtros;

import unq.integrador.IMuestra;
import unq.integrador.enums.Lapso;

import java.time.LocalDate;

public class FiltroFechaDeCreacion extends FiltroPorFecha{

    public FiltroFechaDeCreacion(Lapso lapso, LocalDate fecha) {
        super(lapso, fecha);
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return lapso.comparar(muestra.getFechaCreacion(), fecha);
    }
}
