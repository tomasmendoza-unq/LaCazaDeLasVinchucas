package unq.integrador.impls;

import unq.integrador.*;
import unq.integrador.enums.TipoOrganizacion;

/**
 * Clase que representa a las Organizaciones
 * 
 * @author Díaz Marcos, Mendoza Tomas, Monteros Dario
 */
public class Organizacion implements IOrganizacion{
  private IUbicacion ubicacion;
  private TipoOrganizacion tipo;
  private int empleados;
  private IFuncionalidadExterna funcionalidadMuestra;
  private IFuncionalidadExterna funcionalidadValidacion;
  
  /**
   * Constructor de Organización
   * 
   * @param ubicacion Es la ubicación de la organización
   * @param tipo Es el tipo de la organización
   * @param empleados Es la cantidad de empleados que tiene la organización
   * @param funcionalidadMuestra Es la funcionalidad externa que se va a ejecutar cada vez que se
   *  agrega una nueva muestra en las zonas de cobertura en las que está registrada la organización
   * @param funcionalidadValidacion Es la funcionalidad externa que se va a ejecutar cada vez que se
   *  valide una muestra en las zonas de cobertura en las que está registrada la organización
   */
  public Organizacion(IUbicacion ubicacion, TipoOrganizacion tipo, int empleados, IFuncionalidadExterna funcionalidadMuestra, IFuncionalidadExterna funcionalidadValidacion) {
    this.ubicacion = ubicacion;
    this.tipo = tipo;
    this.empleados = empleados;
    this.funcionalidadMuestra = funcionalidadMuestra;
    this.funcionalidadValidacion = funcionalidadValidacion;
  }

  /**
   * Getter de la ubicación de la organización
   * 
   * @return La ubicación de la organización
   */
  public IUbicacion getUbicacion() {
    return this.ubicacion;
  }

  /**
   * Getter del tipo de la organización
   * 
   * @return El tipo de la organización
   */
  public TipoOrganizacion getTipo() {
    return this.tipo;
  }

  /**
   * Getter de la cantidad de empleados de la ubicación
   * 
   * @return La cantidad de empleados de la ubicación
   */
  public int getCantidadEmpleados() {
    return this.empleados;
  }

  /**
   * Método que cambia la funcionalidad externa que se ejecuta cuando se agrega
   * una nueva muestra en las zonas de cobertura en las que está registrada la organización
   * 
   * @param funcionalidad La nueva funcionalidad para el registro de muestras
   */
  public void cambiarFuncionalidadMuestra(IFuncionalidadExterna funcionalidad){
    this.funcionalidadMuestra = funcionalidad;
  }

  /**
   * Método que cambia la funcionalidad externa que se ejecuta cuando se valida
   * una muestra en las zonas de cobertura en las que está registrada la organización
   * 
   * @param funcionalidad La nueva funcionalidad para la validación de muestras
   */
  public void cambiarFuncionalidadValidacion(IFuncionalidadExterna funcionalidad){
    this.funcionalidadValidacion = funcionalidad;
  }

  /**
   * Método que le indica a una zona de cobertura que la organización quiere recibir
   * notificaciones de esa zona
   * 
   * @param zona Zona de cobertura a la que la organización se quiere registrar
   */
  public void registrarseAZona(IZonaDeCobertura zona){
    zona.registrarOrganizacion(this);
  }

  /**
   * Método que llama a la funcionalidad externa correspondiente cuando se
   * agrega una nueva muestra en las zonas de cobertura en las que está registrada
   * la organización.
   * 
   * @param zona Zona de cobertura en la que se agregó la nueva muestra
   * @param muestra Muestra que se agregó en la zona de cobertura
   */
  public void recibirNotificacionMuestra(IZonaDeCobertura zona, IMuestra muestra){
    this.funcionalidadMuestra.nuevoEvento(this, zona, muestra);
  }

  /**
   * Método que llama a la funcionalidad externa correspondiente cuando se
   * valida una nueva muestra en las zonas de cobertura en las que está registrada
   * la organización.
   * 
   * @param zona Zona de cobertura en la que se agregó la nueva muestra
   * @param muestra Muestra que se validó en la zona de cobertura
   */
  public void recibirNotificacionValidacion(IZonaDeCobertura zona, IMuestra muestra){
    this.funcionalidadValidacion.nuevoEvento(this, zona, muestra);
  }
}
