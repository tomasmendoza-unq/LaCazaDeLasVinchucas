package unq;

import java.util.List;

public interface IUbicacion {
    public double latitud();
    public double longitud();
    public double distancia(IUbicacion ubicacion);
    public List<IUbicacion> ubicacionesAMenosDe(List<IUbicacion> ubicaciones, double distancia);
    public List<IMuestra> muestrasAMenosDe(List<IMuestra> muestras, double distancia);
}
