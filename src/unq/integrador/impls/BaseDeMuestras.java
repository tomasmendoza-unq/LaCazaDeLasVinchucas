package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;

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
public class BaseDeMuestras implements IBaseDeMuestras {

    private List<IMuestra> muestrasNoVerificadas;
    //private List<IMuestra> muestrasVerificadas;
    private List<IZonaDeCobertura> zonaDeCoberturas;

    /**
     * Constructor de BaseDeMuestras
     * Tus únicos dos atributos son ArrayList, uno de IMuestra y otro de
     * IZonaDeCobertura
     */
    public BaseDeMuestras(){
        muestrasNoVerificadas = new ArrayList<>();
        //muestrasVerificadas = new ArrayList<>();
        zonaDeCoberturas = new ArrayList<>();
    }

    /**
     * Método para añador una muestra a la lista de muestras de la base.
     * Además, de una lista de zonas de cobertura que están sobre la ubicación de las muestras
     * les añade la misma a cada una.
     * 
     * @param muestra Una muestra para agregar
     */
    @Override
    public void cargarMuestra(IMuestra muestra) {
        muestrasNoVerificadas.add(muestra);
        this.zonasPertenecientesA(muestra.getUbicacion())
            .forEach(zonaDeCobertura -> zonaDeCobertura.cargarMuestra(muestra));
    }

    /**
     * Método para agregar una zona de cobertura a la lista de zonas
     * 
     * @param zonaDeCobertura Una zona a agregar
     */
    public void RegistrarZona(IZonaDeCobertura zonaDeCobertura){
        zonaDeCoberturas.add(zonaDeCobertura);
    }

    @Override
    public void cargarMuestraVerificada(IMuestra muestra) {
        this.removerMuestra(muestra);
        //muestrasVerificadas.add(muestra);
        this.zonasPertenecientesA(muestra.getUbicacion())
                .forEach(zonaDeCobertura -> zonaDeCobertura.notificarNuevaMuestraVerificada(muestra));
    }

    private void removerMuestra(IMuestra muestra) {
        muestrasNoVerificadas.remove(muestra);
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
    public List<IMuestra> filtrarMuestras(FiltroMuestras filtro) {
        List<IMuestra> resultado = 
        muestrasNoVerificadas.stream()
        .filter(filtro.getPredicate())
        .toList();
        
        return resultado;
    }
}
