package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.IZonaDeCoberturaFactory;

public class ZonaDeCoberturaFactory implements IZonaDeCoberturaFactory {
    private IBaseDeMuestras baseDeMuestras;

    /**
     * Constructor de ZonaDeCoberturFactory
     * 
     * @param baseDeMuestras Una base de muestras
     */
    public ZonaDeCoberturaFactory(IBaseDeMuestras baseDeMuestras){
        this.baseDeMuestras = baseDeMuestras;
    }

    /**
     * Método para crear una zona de cobertura y registrarla en la
     * base de muestras
     * 
     * @param epicentro Una ubicación que será el epicentro de la zona
     * @param nombreDeCobertura Un String que representa el nombre de la zona
     * @param radioEnKm Un double que representa el radio de la zona
     * @return Una zona de cobertura 
     */
    @Override
    public IZonaDeCobertura crearZona(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        IZonaDeCobertura zonaDeCobertura = new ZonaDeCobertura(epicentro,nombreDeCobertura,radioEnKm);
        this.baseDeMuestras.RegistrarZona(zonaDeCobertura);
        return zonaDeCobertura;
    }
}
