package unq.integrador.impls;

import java.util.List;

import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;

/**
 * Clase que representa las ubicaciones de los diferentes elementos del sistema
 * 
 @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Ubicacion implements IUbicacion{
    private double latitud;
    private double longitud;
    private static final double RADIO_TIERRA = 6371;

    /**
     * Constructor de Ubicacion
     * 
     * @param latitud Representa la latitud en grados de la ubicación
     * @param longitud Representa la longitud en grados de la ubicación
     */
    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * Getter de la latitud de la ubicación
     * 
     * @return Double que representa la latitud de la ubicación
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Getter de la longitud de la ubicación
     * 
     * @return Double que representa la longitud de la ubicación
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Función que calcula la distancia de la ubicación en kilómetros con otra dada,
     * utilizando la Fórmula de Haversine
     * 
     * @param ubicacion Ubicación a la cual se va a calcular la distancia
     * @return Double que representa la longitud de la ubicación
     */
    public double distanciaA(IUbicacion ubicacion) {
        double latitud1Rad = Math.toRadians(this.getLatitud());
        double longitud1Rad = Math.toRadians(this.getLongitud());

        double latitud2Rad = Math.toRadians(ubicacion.getLatitud());
        double longitud2Rad = Math.toRadians(ubicacion.getLongitud());

        double diferenciaLatitudes = Math.abs(latitud1Rad - latitud2Rad);
        double diferenciaLongitudes = Math.abs(longitud1Rad - longitud2Rad);

        double a = Math.sin(diferenciaLatitudes / 2) *
                    Math.sin(diferenciaLatitudes / 2)
                    +
                    Math.cos(latitud1Rad) *
                    Math.cos(latitud2Rad) *
                    Math.sin(diferenciaLongitudes / 2) *
                    Math.sin(diferenciaLongitudes / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distancia = RADIO_TIERRA * c;

        return distancia;
    }

    /**
     * Función que, de una lista de muestras, devuelve todas aquellas que se encuentren
     * a una distancia menor a la dada
     * 
     * @param muestras lista de muestras que se va a filtrar
     * @param distancia distancia a la que tienen que estar las muestras de la lista
     * @return Una lista de muestras que están a una distancia menor a la dada
     */
    public List<IMuestra> muestrasAMenosDe(List<IMuestra> muestras, double distancia) {
        List<IMuestra> muestrasCercanas = muestras.stream()
                                            .filter(m -> this.distanciaA(m.getUbicacion()) < distancia)
                                            .toList();
        
        return muestrasCercanas;
    }

    /**
     * Función que, de una lista de ubicaciones, devuelve todas aquellas que se encuentren
     * a una distancia menor a la dada
     * 
     * @param ubicaciones lista de muestras que se va a filtrar
     * @param distancia distancia a la que tienen que estar las ubicaciones de la lista
     * @return Una lista de ubicaciones que están a una distancia menor a la dada
     */
    public List<IUbicacion> ubicacionesAMenosDe(List<IUbicacion> ubicaciones, double distancia) {
        List<IUbicacion> ubicacionesCercanas = ubicaciones.stream()
                                                .filter(u -> this.distanciaA(u) < distancia)
                                                .toList();

        return ubicacionesCercanas;
    }

}
