package unq.integrador;

/**
 * Esta clase representa las muestras verificadas, que son aquellas en
 * las que ningún usuario de la aplicación puede participar.
 * 
 * @author Díaz Marcos, Mendoza Tomás, Monteros Dario
 */
public class MuestraVerificada extends Muestra {
    private Opinion resultado;
    
    /**
     * Constructor de MuestaVerificada
     * 
     * @param user Usuario que publicó la muestra
     * @param fotografia Fotografía sobre la que se opina
     * @param ubicacion Ubicación de donde fue la muestra
     * @param op Opinión que ganó la discución de la foto y por ende responde al resultado
     */
    public MuestraVerificada(IUsuario user, String fotografia, String ubicacion, Opinion op) {
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
        switch (this.resultado) {
            case VINCHUCA_GUASAYANA:
                return "Vinchuca Guasayana";
            
            case VINCHUCA_INFESTANS:
                return "Vinchuca Infestans";
            
            case VINCHUCA_SORDIDA:
                return "Vinchuca Sordida";
            
            case CHINCHA_FOLIADA:
                return "Chincha Foliada";
            
            case PHTIA_CHINCHE:
                return "Phtia Chinche";
            
            case NINGUNA:
                return "Ninguna";
            
            case IMAGEN_POCO_CLARA:
                return "Imagen poco clara";
            
            default:
                return "No definido";
        }
    }

    /**
     * Método que lanza una excepción porque nadie puede opinar en una muestra verificada.
     * 
     * @param op Una opinión que ya no se agrega porque la muestra está verificada.
     */
    @Override
    public void agregarOpinionBasico(Opinion op) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarOpinion'");

        // Lanzar excepción porque en las muestras verificadas no se puede agregar opiniones
    }
    
    /**
     * Método que lanza una excepción porque nadie puede opinar en una muestra verificada.
     * 
     * @param op Una opinión que ya no se agrega porque la muestra está verificada.
     */
    @Override
    public void agregarOpinionExperto(Opinion op) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarOpinion'");

        // Lanzar excepción porque en las muestras verificadas no se puede agregar opiniones
    }


    
}
