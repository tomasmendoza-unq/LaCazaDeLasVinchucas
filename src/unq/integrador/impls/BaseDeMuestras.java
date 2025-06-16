package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

public class BaseDeMuestras implements IBaseDeMuestras {

    private List<IMuestra> muestrasRegistradas;
    private List<IZonaDeCobertura> zonaDeCoberturas;

    public BaseDeMuestras(){
        muestrasRegistradas = new ArrayList<>();
        zonaDeCoberturas = new ArrayList<>();
    }

    @Override
    public void cargarMuestra(IMuestra muestra) {
        muestrasRegistradas.add(muestra);
        this.zonasPertenecientesA(muestra.getUbicacion()).forEach(zonaDeCobertura -> zonaDeCobertura.cargarMuestra(muestra));
    }

    public void agregarZonaDeCobertura(IZonaDeCobertura zonaDeCobertura){
        zonaDeCoberturas.add(zonaDeCobertura);
    }

    private List<IZonaDeCobertura> zonasPertenecientesA(IUbicacion ubicacionMuestra){
        return zonaDeCoberturas.stream().filter(zonaDeCobertura -> zonaDeCobertura.contieneUbicacion(ubicacionMuestra)).toList();
    }
}
