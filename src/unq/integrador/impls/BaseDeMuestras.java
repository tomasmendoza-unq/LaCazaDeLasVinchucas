package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

public class BaseDeMuestras implements IBaseDeMuestras {

    private List<IMuestra> muestrasNoVerificadas;
    private List<IMuestra> muestrasVerificadas;
    private List<IZonaDeCobertura> zonaDeCoberturas;

    public BaseDeMuestras(){
        muestrasNoVerificadas = new ArrayList<>();
        muestrasVerificadas = new ArrayList<>();
        zonaDeCoberturas = new ArrayList<>();
    }

    @Override
    public void cargarMuestra(IMuestra muestra) {
        muestrasNoVerificadas.add(muestra);
        this.zonasPertenecientesA(muestra.getUbicacion())
            .forEach(zonaDeCobertura -> zonaDeCobertura.cargarMuestra(muestra));
    }

    public void RegistrarZona(IZonaDeCobertura zonaDeCobertura){
        zonaDeCoberturas.add(zonaDeCobertura);
    }

    @Override
    public void cargarMuestraVerificada(IMuestra muestra) {
        this.removerMuestra(muestra);
        muestrasVerificadas.add(muestra);
        this.zonasPertenecientesA(muestra.getUbicacion())
                .forEach(zonaDeCobertura -> zonaDeCobertura.notificarNuevaMuestraVerificada(muestra));
    }

    private void removerMuestra(IMuestra muestra) {
        muestrasNoVerificadas.remove(muestra);
    }

    private List<IZonaDeCobertura> zonasPertenecientesA(IUbicacion ubicacionMuestra){
        return zonaDeCoberturas.stream()
            .filter(zonaDeCobertura -> zonaDeCobertura.contieneUbicacion(ubicacionMuestra))
            .toList();
    }
}
