package unq.integrador;

import unq.integrador.enums.TipoOrganizacion;

public interface IOrganizacion {
  public IUbicacion getUbicacion();
  public TipoOrganizacion getTipo();
  public int getCantidadEmpleados();
  public void registrarseAZona(IZonaDeCobertura zona);
  public void cambiarFuncionalidadMuestra(IFuncionalidadExterna funcionalidad);
  public void cambiarFuncionalidadValidacion(IFuncionalidadExterna funcionalidad);
  public void recibirNotificacionMuestra(IZonaDeCobertura zona, IMuestra muestra);
  public void recibirNotificacionValidacion(IZonaDeCobertura zona, IMuestra muestra);
}
