package unq.integrador;

public class MuestraExperto extends Muestra {

    /**
     * Constructor de la clase MuestraExperto
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     */
    public MuestraExperto(IUsuario user, String fotografia, String ubicacion) {
        super(user, fotografia, ubicacion);
    }

    /**
     * Método para conseguir el resultado actual de opiniones
     * 
     * @return La opinion con más votos, que sería la que más 
     * ocurrencias tiene en la lista de opiniones de la clase
     */
    @Override
    public String resultadoActual() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resultadoActual'");
    }

    /**
     * Agregua una opinión a la lista en caso de que sea usuario experto,
     * si fuera usuario básico tiraría una excepción.
     * Si implemento en forma de HashMap, si el contador es 2 
     * para cualquier clave, mutaría en una MuestraCerrada
     * 
     * @param op una opinión para agregar a la lista
     */
    @Override
    public void agregarOpinion(Opinion op) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOpinion'");
    }

}
