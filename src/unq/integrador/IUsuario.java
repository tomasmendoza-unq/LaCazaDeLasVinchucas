package unq.integrador;

import unq.integrador.impls.Opinion;

public interface IUsuario {
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) ;
    public void enviarMuestra(String fotografia, String ubicacion);
    public void determinarRango();
    public boolean subeDeRango();
    public void setMuestraPublicada(IMuestra muestra);
    public int getID();

}
