package unq.integrador.impls;

import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.enums.Lapso;
import unq.integrador.enums.TipoOpinion;

import java.time.LocalDate;
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

    private List<IMuestra> muestrasRegistradas;
    private List<IZonaDeCobertura> zonaDeCoberturas;
    private FiltroDeMuestrasFactory filtroFactory;

    /**
     * Constructor de BaseDeMuestras
     * Tus únicos dos atributos son ArrayList, uno de IMuestra y otro de
     * IZonaDeCobertura
     */
    public BaseDeMuestras(){
        muestrasRegistradas = new ArrayList<>();
        zonaDeCoberturas = new ArrayList<>();
        filtroFactory = new FiltroDeMuestrasFactory();
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
        muestrasRegistradas.add(muestra);
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
    public void cargarMuestraVerificada(IMuestra muestra) {
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
    public List<IMuestra> filtrarMuestras(FiltroMuestras filtro) {
        List<IMuestra> resultado = muestrasRegistradas.stream()
        .filter(filtro.getPredicate())
        .toList();
        
        return resultado;
    }

    /**
	 * Método para crear un filtro indicando el nivel de verificación que debería tener la muestra
	 * 
	 * @param nivel Un booleano que indica si se quiere que la muestra esté verificada o no
	 * @return Un filtro con el criterio dado
	 */
    @Override
    public FiltroMuestras crearFiltroParaNivelDeVerificacion(boolean nivel) {
        return nivel 
        ? this.filtroFactory.crearFiltroParaMuestrasVerificadas()
        : this.filtroFactory.crearFiltroParaMuestrasVotadas();
    }

    /**
	 * Método para crear un filtro indicando desde una fecha que muestras se quieren.
	 * Si las que se crearon después, antes o ese mismo día
	 * 
	 * @param lapso Un lapso que indica a que momento corresponde la búsqueda
	 * @param fecha Una fecha que indica desde donde se tiene en cuenta el filtro 
	 * @return Un filtro con el criterio dado
	 */
    @Override
    public FiltroMuestras crearFiltroParaFechaDeCreacion(Lapso lapso, LocalDate fecha) {
        switch (lapso) {
			case DESPUES:
                return this.filtroFactory.crearFiltroParaFechaDeCreacionDespuesDe(fecha);
            case ANTES:
                return this.filtroFactory.crearFiltroParaFechaDeCreacionAnteriorA(fecha);
			default:
                return this.filtroFactory.crearFiltroParaFechaDeCreacionIgualA(fecha);
		}
    }

    /**
	 * Método para crear un filtro indicando desde una fecha que muestras se 
	 * quieren en base a la última votación que recibieron e indicando si
	 * se crearon después, antes o ese mismo día.
	 * 
	 * @param lapso Un lapso que indica a que momento corresponde la búsqueda
	 * @param fecha Una fecha que indica desde donde se tiene en cuenta el filtro 
	 * @return Un filtro con el criterio dado
	 */
    @Override
    public FiltroMuestras crearFiltroParaFechaDeUltimaVotacion(Lapso lapso, LocalDate fecha) {
		switch (lapso) {
			case DESPUES:
                return this.filtroFactory.crearFiltroParaFechaDeUltimaVotacionDespuesDe(fecha);
			case ANTES:
                return this.filtroFactory.crearFiltroParaFechaDeUltimaVotacionAnteriorA(fecha);
			default:
                return this.filtroFactory.crearFiltroParaFechaDeUltimaVotacionIgualA(fecha);
		}
    }

    /**
	 * Método para crear un filtro basado en el insecto que se registra en la muestra
	 * 
	 * @param tipo Un tipo de opinión que se pueda llegar a dar en la muestra.
	 * @return Un filtro con el criterio dado
	 */
    @Override
    public FiltroMuestras crearFiltroParaInsectoDetectado(TipoOpinion tipo) {
        return this.filtroFactory.crearFiltroParaInsectoDetectado(tipo.imprimirTipo());
    }

    
}
