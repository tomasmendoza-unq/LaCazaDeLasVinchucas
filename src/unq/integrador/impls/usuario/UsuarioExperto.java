package unq.integrador.impls.usuario;

import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;
import unq.integrador.impls.Opinion;

/**
 * Clase que representa la categoría básica entre los usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class UsuarioExperto extends UsuarioRango {

	/**
     * Método para que un usuario de categoría experta
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
		muestra.agregarOpinionExperto(usuario,opinion);
	}

	protected UsuarioRango determinarRango(IUsuario usuario) {
		return usuario.subeDeRango() ? this : new UsuarioBasico();
	}
}
