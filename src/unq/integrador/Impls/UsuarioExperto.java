package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IUsuarioRango;

public class UsuarioExperto implements IUsuarioRango {

	@Override
	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
		muestra.agregarOpinionExperto(opinion);
	}
}
