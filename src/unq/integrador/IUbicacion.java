package unq.integrador;

import java.util.List;

public interface IUbicacion {
    public double getLatitud();
    public double getLongitud();
    public double distanciaA(IUbicacion ubicacion);
    public List<IUbicacion> ubicacionesAMenosDe(List<IUbicacion> ubicaciones, double distancia);
    public List<IMuestra> muestrasAMenosDe(List<IMuestra> muestras, double distancia);
}
