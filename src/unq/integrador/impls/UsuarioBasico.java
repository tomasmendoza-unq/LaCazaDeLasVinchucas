package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;

/**
 * Clase que representa la categoría básica entre los usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class UsuarioBasico extends UsuarioRango {

    /**
     * Método para que un usuario de categoría básica
     * opine de esa forma en una muestra dada
     *
     * @param muestra Muestra sobre la que se opina
     * @param opinion Opinión que se agrega a la muestra
     * @param usuario
     * @throws SinAccesoAMuestraException
     * @throws UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException
     * @throws UnUsuarioNoPuedeOpinarEnSuMuestraException
     */
    @Override
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion, IUsuario usuario) throws UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException {
        muestra.agregarOpinionBasico(usuario,opinion);
    }

    /**
     * Método para que un usuario determine su proximo rango
     *
     * @param usuario Usuario al cual se le modifica el rango
     * @return rango para settear al usuario dado
     */
    @Override
    protected UsuarioRango determinarRango(IUsuario usuario) {
        return usuario.subeDeRango() ? new UsuarioExperto() : this;
    }

}
