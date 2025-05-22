package unq.integrador;

public interface IUsuario {
    public void publicarMuestra(String fotografia, String ubicacion);
    public void opinarDeMuestra(IMuestra muestra, Opinion op);
    public void setMuestraPublicada(IMuestra muestra);
}
