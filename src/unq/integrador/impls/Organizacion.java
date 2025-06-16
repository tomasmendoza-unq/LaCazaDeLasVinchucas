package unq.integrador.impls;

import unq.integrador.*;
import unq.integrador.enums.TipoOrganizacion;

public class Organizacion implements IOrganizacion{
  private IUbicacion ubicacion;
  private TipoOrganizacion tipo;
  private int empleados;
  private IFuncionalidadExterna funcionalidadMuestra;
  private IFuncionalidadExterna funcionalidadValidacion;
  
  public Organizacion(IUbicacion ubicacion, TipoOrganizacion tipo, int empleados, IFuncionalidadExterna funcionalidadMuestra, IFuncionalidadExterna funcionalidadValidacion) {
    this.ubicacion = ubicacion;
    this.tipo = tipo;
    this.empleados = empleados;
    this.funcionalidadMuestra = funcionalidadMuestra;
    this.funcionalidadValidacion = funcionalidadValidacion;
  }

  public IUbicacion getUbicacion() {
    return ubicacion;
  }

  public TipoOrganizacion getTipo() {
    return tipo;
  }

  public int getCantidadEmpleados() {
    return empleados;
  }

  public void cambiarFuncionalidadMuestra(IFuncionalidadExterna funcionalidad){
    this.funcionalidadMuestra = funcionalidad;
  }

  public void cambiarFuncionalidadValidacion(IFuncionalidadExterna funcionalidad){
    this.funcionalidadValidacion = funcionalidad;
  }

  public void registrarseAZona(IZonaDeCobertura zona){
    zona.registrarOrganizacion(this);
  }

  public void recibirNotificacionMuestra(IZonaDeCobertura zona, IMuestra muestra){
    funcionalidadMuestra.nuevoEvento(this, zona, muestra);
  }

  public void recibirNotificacionValidacion(IZonaDeCobertura zona, IMuestra muestra){
    funcionalidadValidacion.nuevoEvento(this, zona, muestra);
  }
}
