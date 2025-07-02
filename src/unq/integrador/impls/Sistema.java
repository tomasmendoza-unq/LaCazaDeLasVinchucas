package unq.integrador.impls;

import unq.integrador.*;
import unq.integrador.error.SinAccesoAMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarEnSuMuestraException;
import unq.integrador.error.UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa las bases de muestras
 * que registran todas las muestras que se crean.
 * <br><br>
 * Adicionalmente avisando a las zonas de cobertura 
 * a las que pertenecen dichas muestras.
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Sistema implements ISistema {

    private List<IMuestra> muestrasRegistradas;
    private List<IZonaDeCobertura> zonaDeCoberturas;

    /**
     * Constructor de Sistema
     * Tus únicos dos atributos son ArrayList, uno de IMuestra y otro de
     * IZonaDeCobertura
     */
    public Sistema(){
        muestrasRegistradas = new ArrayList<>();
        zonaDeCoberturas = new ArrayList<>();
    }

    /**
     * Método para añadir una muestra a la lista de muestras de la base, cargando la muestra al usuario dado.
     * 
     * @param muestra Una muestra para agregar
     * @param usuario usuario que agrego la muestra
     */
    @Override
    public void cargarMuestra(IMuestra muestra, IUsuario usuario) {
        muestrasRegistradas.add(muestra);
        usuario.agregarMuestraPublicada(muestra);
        this.notificarCargaDeUnaMuestra(muestra);
    }

    /**
     * Método para opinar sobre una muestra dada con el usuario dado y la opinion dada.
     *
     * @param muestra Una muestra sobre la cual se opina
     * @param usuario usuario que opina sobre opinion
     * @param opinion opinion que se agrega al usuario
     */
    @Override
    public void opinarSobre(IMuestra muestra, IUsuario usuario, Opinion opinion) throws SinAccesoAMuestraException, UnUsuarioNoPuedeOpinarEnSuMuestraException, UnUsuarioNoPuedeOpinarNuevamenteEnUnaMuestraException {
        usuario.opinarSobreUnaMuestra(muestra,opinion);
    }

    /**
     * Metodo para cargar la muestra a las zonas de cobertura que se ubique en el radio.
     *
     * @param muestra muestra a agregar a la zona
     */
    private void notificarCargaDeUnaMuestra(IMuestra muestra) {
        this.zonasPertenecientesA(muestra.getUbicacion())
                .forEach(zonaDeCobertura -> zonaDeCobertura.cargarMuestra(muestra));
    }


    /**
     * Método que notifica a todas las zonas de cobertura que pertenecen
     * a la ubicación de la muestra que esta se encuentra verificada
     * 
     * @param muestra Una muestra que esté verificada
     */
    @Override
    public void notificarVerificacion(IMuestra muestra) {
        this.zonasPertenecientesA(muestra.getUbicacion())
            .forEach(zonaDeCobertura -> zonaDeCobertura.notificarNuevaMuestraVerificada(muestra));
    }

    /**
     * Método para agregar una zona de cobertura a la lista de zonas
     * 
     * @param zonaDeCobertura Una zona a agregar
     */
    public void RegistrarZona(IZonaDeCobertura zonaDeCobertura){
        zonaDeCoberturas.add(zonaDeCobertura);
    }
    
    /**
     * Método para buscar que zonas de la lista de zonas
     * coinciden con la ubicación de la muestra
     * 
     * @param ubicacionMuestra La ubicación de una muestra conocida
     * @return Una lista de IZonaDeCobertura
     */
    private List<IZonaDeCobertura> zonasPertenecientesA(IUbicacion ubicacionMuestra){
        return zonaDeCoberturas.stream()
                .filter(zonaDeCobertura -> zonaDeCobertura.contieneUbicacion(ubicacionMuestra))
                .toList();
    }

    /**
     * Método para conseguir las muestras que pasen por el filtro si es que cumplen la condición
     * 
     * @param filtro Un filtro que será aplicado a las muestras
     * @return Una lista de muestras que pasaron el filtro
     */
    @Override
    public List<IMuestra> filtrarMuestras(IFiltroMuestra filtro) {
        return muestrasRegistradas.stream().filter(filtro::verificar).toList();
    }

    
}
