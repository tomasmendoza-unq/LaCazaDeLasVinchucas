package unq.integrador;

public interface IZonaDeCobertura {
    public String getNombreDeCobertura();
    public IUbicacion getEpicentro();
    public double getRadioEnKm();
    public void cargarMuestra(IMuestra muestra);
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura);
}
