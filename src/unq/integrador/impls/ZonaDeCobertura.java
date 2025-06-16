package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.Muestra;

import java.util.ArrayList;
import java.util.List;

public class ZonaDeCobertura implements IZonaDeCobertura {

    private IUbicacion epicentro;
    private double radioEnKm;
    private String nombreDeCobertura;
    private List<IMuestra> muestras;

    public ZonaDeCobertura(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        this.epicentro = epicentro;
        this.muestras = new ArrayList<>();
        this.nombreDeCobertura = nombreDeCobertura;
        this.radioEnKm = radioEnKm;
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


}
