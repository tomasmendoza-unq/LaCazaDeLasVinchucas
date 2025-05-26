package unq.integrador.Impls;

import unq.integrador.Enums.Opinion;
import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUsuario;
import unq.integrador.IUsuarioRango;

public  class Usuario implements IUsuario {

	private IBaseDeMuestras bdm;
	private IUsuarioRango participanteState;
	
	public Usuario(IBaseDeMuestras bdm, IUsuarioRango state) {
		this.bdm = bdm;
		this.participanteState = state;
	}

	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
           participanteState.opinarSobreUnaMuestra(muestra,opinion);
    }

	public void enviarMuestra(IMuestra muesta) {
		bdm.agregarMuestra(muesta);
	}
	
}
