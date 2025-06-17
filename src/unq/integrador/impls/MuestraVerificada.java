package unq.integrador.impls;

import unq.integrador.IEstadoDeMuestra;
import unq.integrador.enums.TipoOpinion;
import unq.integrador.error.SinAccesoAMuestraException;

/**
 * Esta clase representa las muestras verificadas, que son aquellas en
 * las que ningún usuario de la aplicación puede participar.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraVerificada implements IEstadoDeMuestra {
    private TipoOpinion resultado;
    
    /**
     * Constructor de MuestaVerificada
     * 
     * @param op Tipo de opinión que ganó la discución de la fotografía
     */
    public MuestraVerificada(TipoOpinion op) {
        this.resultado = op;
    }

    /**
     * Método para conseguir la opinión con más votos.
     * 
     * @return Un String con la opinión verificada por expertos
     */
    @Override
    public String resultadoActual() {
        return this.resultado.imprimirTipo();
    }

    /**
     * Método que lanza una excepción porque nadie puede opinar en una muestra verificada.
     * 
     * @param op Una opinión que ya no se agrega porque la muestra está verificada.
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        throw new SinAccesoAMuestraException("Los usuarios no pueden opinar en una muestra verificada");
    }
    
    /**
     * Método que lanza una excepción porque nadie puede opinar en una muestra verificada.
     * 
     * @param op Una opinión que ya no se agrega porque la muestra está verificada.
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        throw new SinAccesoAMuestraException("Los usuarios no pueden opinar en una muestra verificada");
    }


    
}
