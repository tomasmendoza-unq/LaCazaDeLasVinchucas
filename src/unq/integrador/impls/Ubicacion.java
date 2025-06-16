package unq.integrador.impls;

import java.util.List;

import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;

public class Ubicacion implements IUbicacion{
    private double latitud;
    private double longitud;
    private static final double RADIO_TIERRA = 6371;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

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

    public List<IMuestra> muestrasAMenosDe(List<IMuestra> muestras, double distancia) {
        List<IMuestra> muestrasCercanas = muestras.stream()
                                            .filter(m -> this.distanciaA(m.ubicacion()) < distancia)
                                            .toList();
        
        return muestrasCercanas;
    }

    public List<IUbicacion> ubicacionesAMenosDe(List<IUbicacion> ubicaciones, double distancia) {
        List<IUbicacion> ubicacionesCercanas = ubicaciones.stream()
                                                .filter(u -> this.distanciaA(u) < distancia)
                                                .toList();

        return ubicacionesCercanas;
    }

}
