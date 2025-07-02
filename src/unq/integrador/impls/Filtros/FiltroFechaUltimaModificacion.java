package unq.integrador.impls.Filtros;

import unq.integrador.IMuestra;
import unq.integrador.enums.Lapso;

import java.time.LocalDate;

public class FiltroFechaUltimaModificacion extends FiltroPorFecha{
    public FiltroFechaUltimaModificacion(Lapso lapso, LocalDate fecha) {
        super(lapso, fecha);
    }

    @Override
    public boolean verificar(IMuestra muestra) {
        return lapso.comparar(muestra.getFechaUltimaVotacion(), fecha);
    }
}
