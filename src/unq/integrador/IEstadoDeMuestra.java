package unq.integrador;

import unq.integrador.impls.Opinion;

public interface IEstadoDeMuestra {
    public String resultadoActual();
    public void agregarOpinionBasico(Opinion op);
    public void agregarOpinionExperto(Opinion op);
    public boolean esVerificada();
}
