package unq.integrador.impls;

import unq.integrador.*;

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
    private String nombreDeCobertura;
    private double radioEnKm;
    private List<IMuestra> muestras;
    private List<IOrganizacion> organizacionesInteresadas;

    /**
     * Constructor de ZonaDeCobertura
     *
     * @param epicentro Representa la ubicación central de la zona de cobertura
     * @param nombreDeCobertura Representa el nombre de la zona de cobertura
     * @param radioEnKm Representa la distancia en Km del epicentro a algún extremo de la zona
     */
    public ZonaDeCobertura(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm) {
        this.epicentro                 = epicentro;
        this.nombreDeCobertura         = nombreDeCobertura;
        this.radioEnKm                 = radioEnKm;
        this.muestras                  = new ArrayList<>();
        this.organizacionesInteresadas = new ArrayList<>();
    }

    /**
     * Getter del epicentro de la zona
     *
     * @return Ubicacion de la zona
     */
    @Override
    public IUbicacion getEpicentro() {
        return this.epicentro;
    }

    /**
     * Getter del nombre de la zona
     *
     * @return Nombre de la zona
     */
    @Override
    public String getNombreDeCobertura() {
        return this.nombreDeCobertura;
    }

    /**
     * Getter del radio de la zona
     *
     * @return Double - Distancia del radio
     */
    @Override
    public double getRadioEnKm() {
        return this.radioEnKm;
    }

    /**
     * Método para agregar una muestra a la zona de cobertura
     *
     * @param muestra Una muestra a agregar
     */
    @Override
    public void cargarMuestra(IMuestra muestra) {
        this.muestras.add(muestra);
        this.notificarNuevaMuestra(muestra);
    }

    /**
     * Método que notifica a todas las organizaciones interesadas
     * en la zona que se cargó una nueva muestra
     * 
     * @param muestra Una muestra nueva
     */
    private void notificarNuevaMuestra(IMuestra muestra) {
        this.organizacionesInteresadas
        .forEach(organizacion -> organizacion.recibirNotificacionMuestra(this, muestra));
    }

    /**
     * Método que notifica a todas las organizaciones interesadas
     * en la zona que se cargó una muestra quedó verificada
     * 
     * @param muestra Una muestra verificada
     */
    @Override
    public void notificarNuevaMuestraVerificada(IMuestra muestra){
        this.organizacionesInteresadas
        .forEach(organizacion -> organizacion.recibirNotificacionValidacion(this,muestra));
    }

    /**
     * Método que indica si una zona de cobertura se solapa con otra
     *
     * @param zonaDeCobertura Otra zona para comparar las distancias
     */
    @Override
    public boolean seSolapaCon(IZonaDeCobertura zonaDeCobertura) {
        double distanciaA = this.epicentro.distanciaA(zonaDeCobertura.getEpicentro());
        return distanciaA <= (this.getRadioEnKm() + zonaDeCobertura.getRadioEnKm());
    }

    /**
     * Método que indica si una ubicación
     * está contenida en el área de la zona
     * 
     * @param ubicacion Una ubicacion para comparar
     * @return {@code true} si está contenida, {@code false} si está por fuera
     */
    @Override
    public boolean contieneUbicacion(IUbicacion ubicacion) {
        return this.epicentro.distanciaA(ubicacion) <= this.radioEnKm;
    }
    /**
     * Método para agregar una organización interesada
     * en la zona de cobertura
     *
     * @param organizacion Una organización a agregar
     */

    /**
     * Método para agregar una organización a
     * la lista de organizaciones interesadas en la zona
     * 
     * @param organizacion Una organización interesada
     */
    @Override
    public void registrarOrganizacion(IOrganizacion organizacion) {
        this.organizacionesInteresadas.add(organizacion);
    }
    
    /**
     * Método para eliminar una organización interesada
     * en la zona de cobertura
     *
     * @param organizacion Una organización a agregar
     */
    @Override
    public void eliminarOrganizacion(IOrganizacion organizacion) {
        this.organizacionesInteresadas.remove(organizacion);
    }
}
