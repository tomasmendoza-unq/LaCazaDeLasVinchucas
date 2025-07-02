
package unq.integrador.enums;

import java.time.LocalDate;

public enum Lapso {
    ANTES {
        @Override
        public boolean comparar(LocalDate fechaMuestra, LocalDate fechaFiltro) {
            return fechaMuestra.isBefore(fechaFiltro);
        }
    },
    DESPUES {
        @Override
        public boolean comparar(LocalDate fechaMuestra, LocalDate fechaFiltro) {
            return fechaMuestra.isAfter(fechaFiltro);
        }
    },
    IGUAL {
        @Override
        public boolean comparar(LocalDate fechaMuestra, LocalDate fechaFiltro) {
            return fechaMuestra.equals(fechaFiltro);
        }
    };
    public abstract boolean comparar(LocalDate fechaMuestra, LocalDate fechaFiltro);
}