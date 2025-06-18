package unq.integrador;

import unq.integrador.enums.TipoOpinion;
import unq.integrador.impls.Opinion;
/**
 * Interfaz de Usuarios
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public interface IUsuario {
    public void opinarSobreUnaMuestra(IMuestra muestra, TipoOpinion tipoOpinion) ;
    public void enviarMuestra(String fotografia, IUbicacion ubicacion);
    public void determinarRango();
    public boolean subeDeRango();
    public void quitarMuestra(IMuestra muestra);
    public void agregarMuestraPublicada(IMuestra muestra);
    public boolean publicoEstaMuestra(IMuestra muestra);

    int getId();
}
