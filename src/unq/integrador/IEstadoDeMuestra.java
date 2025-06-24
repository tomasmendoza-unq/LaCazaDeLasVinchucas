package unq.integrador;

import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.impls.Opinion;

public interface IEstadoDeMuestra {
    public String resultadoActual();
    public void agregarOpinionBasico(Opinion op) throws SinAccesoAMuestraException;
    public void agregarOpinionExperto(Opinion op) throws SinAccesoAMuestraException;
    public boolean esVerificada();
}
