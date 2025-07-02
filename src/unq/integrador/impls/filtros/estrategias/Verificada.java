package unq.integrador.impls.filtros.estrategias;

import unq.integrador.IMuestra;

public class Verificada extends EstrategiaVerificacion{
    @Override
    public boolean verificar(IMuestra muestra) {
        return false;
    }
}
