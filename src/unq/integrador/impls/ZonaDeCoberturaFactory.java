package unq.integrador.impls;

import unq.integrador.ISistema;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.IZonaDeCoberturaFactory;

public class ZonaDeCoberturaFactory implements IZonaDeCoberturaFactory {
    private ISistema sistema;

    /**
     * Constructor de ZonaDeCoberturFactory
     * 
     * @param sistema referencia hacia el sistema
     */
    public ZonaDeCoberturaFactory(ISistema sistema){
        this.sistema = sistema;
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
        this.sistema.RegistrarZona(zonaDeCobertura);
        return zonaDeCobertura;
    }
}
