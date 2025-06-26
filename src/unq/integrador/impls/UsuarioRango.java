package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;

/**
 * Clase abstracta que representa el rango de los usuarios
 * Desde esta clase se delega a UsuarioBasico, UsuarioExperto
 * y UsuarioEspecialista
 */
public abstract class UsuarioRango {

    public abstract void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) throws UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException, SinAccesoAMuestraException;
    /**
     * Método para que un usuario determine su proximo rango
     *
     * @param usuario Usuario al cual se le modifica el rango
     */
    public final void determinarSiguienteRango(IUsuario usuario) {
        usuario.setProximoRango(this.determinarRango(usuario));
    }

    /**
     * Método para que un usuario determine su proximo rango
     *
     * @param usuario Usuario al cual se le modifica el rango
     * @return rango para settear al usuario dado
     */
    protected abstract UsuarioRango determinarRango(IUsuario usuario);
}
