package unq.integrador.Impls;

import unq.integrador.IMuestra;
import unq.integrador.IOpinion;
import unq.integrador.IUsuarioRango;

public class UsuarioBasico implements IUsuarioRango {


    @Override
    public void opinarSobreUnaMuestra(IMuestra muestra, IOpinion opinion) {
        muestra.agregarOpinionBasico(opinion);
    }
}
