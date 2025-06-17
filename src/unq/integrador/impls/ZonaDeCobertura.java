package unq.integrador.impls;

import unq.integrador.*;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCobertura implements IZonaDeCobertura {

    private IUbicacion epicentro;
    private double radioEnKm;
    private String nombreDeCobertura;
    private List<IMuestra> muestras;
    private List<IOrganizacion> organizacionesInteresadas;

    public ZonaDeCobertura(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        this.epicentro = epicentro;
        this.muestras = new ArrayList<>();
        this.nombreDeCobertura = nombreDeCobertura;
        this.radioEnKm = radioEnKm;
        this.organizacionesInteresadas = new ArrayList<>();
    }

    @Override
    public String getNombreDeCobertura() {
        return nombreDeCobertura;
    }

    @Override
    public IUbicacion getEpicentro() {
        return epicentro;
    }

    @Override
    public double getRadioEnKm() {
        return radioEnKm;
    }

    @Override
    public void cargarMuestra(IMuestra muestra) {
        muestras.add(muestra);
        this.notificarNuevaMuestra(muestra);
    }

    private void notificarNuevaMuestra(IMuestra muestra) {
        organizacionesInteresadas.forEach(organizacion -> organizacion.recibirNotificacionMuestra(this, muestra));
    }

    @Override
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura) {
        double distanciaA = epicentro.distanciaA(zonaDeCobertura.getEpicentro());
        return distanciaA <= (this.getRadioEnKm() + zonaDeCobertura.getRadioEnKm());
    }

    @Override
    public boolean contieneUbicacion(IUbicacion ubicacionMuestra) {
        return epicentro.distanciaA(ubicacionMuestra) <= radioEnKm;
    }

    @Override
    public void registrarOrganizacion(IOrganizacion organizacion) {
        organizacionesInteresadas.add(organizacion);
    }

    @Override
    public void eliminarOrganizacion(IOrganizacion organizacion) {
        organizacionesInteresadas.remove(organizacion);
    }


}
