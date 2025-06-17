package unq.integrador.impls;

import unq.integrador.IMuestra;
import unq.integrador.IOrganizacion;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las zonas de coberturas, que contienen un epicentro
 * un radio en Km, el nombre de la misma. También contiene las muestras que se registraron
 * en la misma y las organizaciones que están registradas en la zona
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class ZonaDeCobertura implements IZonaDeCobertura {

    private IUbicacion epicentro;
    private double radioEnKm;
    private String nombreDeCobertura;
    private List<IMuestra> muestras;
    private List<IOrganizacion> organizacionesSuscritas;

    /**
     * Constructor de ZonaDeCobertura
     * 
     * @param epicentro Representa la ubicación central de la zona de cobertura 
     * @param nombreDeCobertura Representa el nombre de la zona de cobertura
     * @param radioEnKm Representa la distancia en Km del epicentro a algún extremo de la zona
     */
    public ZonaDeCobertura(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        this.epicentro = epicentro;
        this.muestras = new ArrayList<>();
        this.nombreDeCobertura = nombreDeCobertura;
        this.radioEnKm = radioEnKm;
        this.organizacionesSuscritas = new ArrayList<IOrganizacion>();
    }

    /**
     * Getter del nombre de la zona
     * 
     * @return Nombre de la zona
     */
    @Override
    public String getNombreDeCobertura() {
        return nombreDeCobertura;
    }

    /**
     * Getter del epicentro de la zona
     * 
     * @return Ubicacion de la zona 
     */
    @Override
    public IUbicacion getEpicentro() {
        return epicentro;
    }

    /**
     * Getter del radio de la zona
     * 
     * @return Double - Distancia del radio 
     */
    @Override
    public double getRadioEnKm() {
        return radioEnKm;
    }

    /**
     * Método para agregar una muestra a la zona de cobertura
     * 
     * @param muestra Una muestra a agregar
     */
    @Override
    public void cargarMuestra(IMuestra muestra) {
        muestras.add(muestra);
    }

    /**
     * Método para agregar una organización interesada 
     * en la zona de cobertura
     * 
     * @param organizacion Una organización a agregar
     */
    @Override
    public void registrarOrganizacion(IOrganizacion organizacion){
        organizacionesSuscritas.add(organizacion);
    }

    /**
     * Método que indica si una zona de cobertura se solapa con otra
     * 
     * @param zonaDeCobertura Otra zona para comparar las distancias
     */
    @Override
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura) {
        double distanciaA = epicentro.distanciaA(zonaDeCobertura.getEpicentro());
        return distanciaA <= (this.getRadioEnKm() + zonaDeCobertura.getRadioEnKm());
    }

    /**
     * Método que indica si una ubicación dada se encuentra dentro de la zona de cobertura
     * 
     * @param ubicacionMuestra Ubicación que se va a verificar si se encuentra dentro de la zona
     * @return Un booleano que indica si la zona de cobertura contiene la ubicación
     */
    @Override
    public boolean contieneUbicacion(IUbicacion ubicacionMuestra) {
        return epicentro.distanciaA(ubicacionMuestra) <= radioEnKm;
    }


}
