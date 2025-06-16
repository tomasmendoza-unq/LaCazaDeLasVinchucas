package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.IZonaDeCoberturaFactory;

public class ZonaDeCoberturaFactory implements IZonaDeCoberturaFactory {
    private IBaseDeMuestras baseDeMuestras;

    public ZonaDeCoberturaFactory(IBaseDeMuestras baseDeMuestras){
        this.baseDeMuestras = baseDeMuestras;
    }


    @Override
    public IZonaDeCobertura crearZona(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        IZonaDeCobertura zonaDeCobertura = new ZonaDeCobertura(epicentro,nombreDeCobertura,radioEnKm);
        baseDeMuestras.RegistrarZona(zonaDeCobertura);
        return zonaDeCobertura;
    }
}
