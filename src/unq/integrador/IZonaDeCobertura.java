package unq.integrador;

import unq.integrador.impls.Organizacion;

public interface IZonaDeCobertura {
    public String getNombreDeCobertura();
    public IUbicacion getEpicentro();
    public double getRadioEnKm();
    public void cargarMuestra(IMuestra muestra);
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura);
    boolean contieneUbicacion(IUbicacion ubicacionMuestra);
    void registrarOrganizacion(IOrganizacion organizacion);
    void eliminarOrganizacion(IOrganizacion organizacion);
}
