package unq.integrador.impls.Filtros;

import unq.integrador.IMuestra;

public class FiltroNivelDeVerificacion extends FiltroBasico{
    @Override
    public boolean verificar(IMuestra muestra) {
        return muestra.esVerificada();
    }
}
