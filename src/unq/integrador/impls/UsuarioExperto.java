package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IUsuarioRango;

/**
 * Clase que representa la categoría básica entre los usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class UsuarioExperto implements IUsuarioRango {

	/**
     * Método para que un usuario de categoría experta
     * opine de esa forma en una muestra dada
     * 
     * @param muestra Muestra sobre la que se opina
     * @param opinion Opinión que se agrega a la muestra
     */
	@Override
	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
        opinion.setCategoria("Experto");
		muestra.agregarOpinionExperto(opinion);
	}
}
