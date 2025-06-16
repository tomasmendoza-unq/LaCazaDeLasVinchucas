package unq.integrador;

public interface IZonaDeCoberturaFactory {
    public IZonaDeCobertura crearZona(IUbicacion epicentro, String nombreDeCobertura, double radioEnKm);
}
