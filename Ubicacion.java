package unq;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion implements IUbicacion{
    double latitud;
    double longitud;

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public double latitud() {
        return latitud;
    }


    public double longitud() {
        return longitud;
    }

    public double distancia(IUbicacion ubicacion) {
        double distanciaX = Math.abs(this.longitud() - ubicacion.longitud());
        double distanciaY = Math.abs(this.latitud() - ubicacion.latitud());
        double distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));

        return distancia;
    }

    public List<IMuestra> muestrasAMenosDe(List<IMuestra> muestras, double distancia) {
        List<IMuestra> muestrasCercanas = new ArrayList<IMuestra>();
        double distanciaALaMuestraActual;
        for (int i = 0; i < muestras.size(); i++){
            distanciaALaMuestraActual = this.distancia(muestras.get(i).ubicacion());
            if(distanciaALaMuestraActual < distancia){

            }
        }
        return muestrasCercanas;
    }

    public List<IUbicacion> ubicacionesAMenosDe(List<IUbicacion> ubicaciones, double distancia) {
        List<IUbicacion> ubicacionesCercanas = new ArrayList<IUbicacion>();

        for(int i = 0; i < ubicaciones.size(); i++){
            if(this.distancia(ubicaciones.get(i)) < distancia){
                ubicacionesCercanas.add(ubicaciones.get(i));
            }
        }
        
        return ubicacionesCercanas;
    }

}
