package unq.integrador;

import java.util.ArrayList;

public class MuestraExperto extends Muestra  {

    /**
     * Constructor de la clase MuestraExperto
     * @param user usuario que publicó la muestra
     * @param fotografia fotografía del usuario que publicó la muestra
     * @param ubicacion ubicación del usuario que publicó la muestra
     * @param opiniones lista de opiniones con la opinión de UN experto
     */
    public MuestraExperto(Usuario user, String fotografia, String ubicacion, ArrayList<Opinion> opiniones) {
        super(user, fotografia, ubicacion);
        this.opiniones = opiniones;
    }

    /**
     * Método para conseguir el resultado actual de opiniones
     * 
     * @return La opinion con más votos, que sería la que más 
     * ocurrencias tiene en la lista de opiniones de la clase
     */
    @Override
    public Opinion resultadoActual() {
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
     * @param user usuario que agrega la opinión
     */
    @Override
    public void addOpinion(Opinion op, Usuario user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOpinion'");
    }

}
