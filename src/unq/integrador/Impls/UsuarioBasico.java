package unq.integrador.Impls;

import unq.integrador.Enums.Opinion;
import unq.integrador.IMuestra;
import unq.integrador.IUsuarioRango;

public class UsuarioBasico implements IUsuarioRango {


    @Override
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) {
        muestra.agregarOpinionBasico(opinion);
    }
}
