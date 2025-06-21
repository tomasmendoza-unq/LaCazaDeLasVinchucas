package unq.integrador;

public interface IZonaDeCobertura {
    public String getNombreDeCobertura();
    public IUbicacion getEpicentro();
    public double getRadioEnKm();
    public void cargarMuestra(IMuestra muestra);
    public void notificarNuevaMuestraVerificada(IMuestra muestra);
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura);
    public boolean contieneUbicacion(IUbicacion ubicacionMuestra);
    public void registrarOrganizacion(IOrganizacion organizacion);
    public void eliminarOrganizacion(IOrganizacion organizacion);
}
