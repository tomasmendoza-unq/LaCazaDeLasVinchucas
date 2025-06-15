package unq.integrador.Impls;

import unq.integrador.IUsuario;
import unq.integrador.Muestra;
import unq.integrador.Exceptions.SinAccesoAMuestraException;
import unq.integrador.Enums.TipoOpinion;

/**
 * Esta clase representa las muestras verificadas, que son aquellas en
 * las que ningún usuario de la aplicación puede participar.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraVerificada extends Muestra {
    private TipoOpinion resultado;
    
    /**
     * Constructor de MuestaVerificada
     * 
     * @param user Usuario que publicó la muestra
     * @param fotografia Fotografía sobre la que se opina
     * @param ubicacion Ubicación de donde fue la muestra
     * @param op Opinión que ganó la discución de la foto y por ende responde al resultado
     */
    public MuestraVerificada(IUsuario user, String fotografia, String ubicacion, TipoOpinion op) {
        super(user, fotografia, ubicacion);
        this.resultado = op;
    }

    /**
     * Método para conseguir la opinión con más votos.
     * 
     * @return retorna el resultado actual que sería la clave del diccionario con el valor más alto
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
