package unq.integrador;

import unq.integrador.impls.Opinion;
/**
 * Interfaz de Usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IUsuario {
    public void opinarSobreUnaMuestra(IMuestra muestra, Opinion opinion) ;
    public void enviarMuestra(String fotografia, String ubicacion);
    public void determinarRango();
    public boolean subeDeRango();
    public void quitarMuestra(IMuestra muestra);
    public void agregarMuestraPublicada(IMuestra muestra);
    public int getID();

}
