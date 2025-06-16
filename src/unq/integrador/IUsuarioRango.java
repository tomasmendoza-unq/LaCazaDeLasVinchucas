package unq.integrador;

import unq.integrador.impls.Opinion;

/**
 * Interfaz del rango de los usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IUsuarioRango {
	void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion);
}
