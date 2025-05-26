package unq.integrador;

import unq.integrador.Enums.Opinion;

public interface IUsuario {
	public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) ;
	public void enviarMuestra(IMuestra muesta);
}
