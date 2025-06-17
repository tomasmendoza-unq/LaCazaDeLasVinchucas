package unq.integrador.testOrganizacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IFuncionalidadExterna;
import unq.integrador.IMuestra;
import unq.integrador.IOrganizacion;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.enums.TipoOrganizacion;
import unq.integrador.impls.Organizacion;
import unq.integrador.impls.ZonaDeCobertura;

public class OrganizacionTest {
  private IOrganizacion organizacion;
  private IUbicacion ubicacion;
  private IZonaDeCobertura zonaDeCobertura;
  private IMuestra muestra;
  private IFuncionalidadExterna funcionalidadMuestra;
  private IFuncionalidadExterna funcionalidadMuestra2;
  private IFuncionalidadExterna funcionalidadValidacion;
  private IFuncionalidadExterna funcionalidadValidacion2;
  
  @BeforeEach
  public void setUp(){
    ubicacion = mock(IUbicacion.class);

    funcionalidadMuestra = mock(IFuncionalidadExterna.class);
    funcionalidadMuestra2 = mock(IFuncionalidadExterna.class);
    funcionalidadValidacion = mock(IFuncionalidadExterna.class);
    funcionalidadValidacion2 = mock(IFuncionalidadExterna.class);

    organizacion = new Organizacion(ubicacion, TipoOrganizacion.ASISTENCIA, 10, funcionalidadMuestra, funcionalidadValidacion);

    zonaDeCobertura = mock(ZonaDeCobertura.class);
    muestra = mock(IMuestra.class);

  }

  @Test
  public void testGetters(){
    assertEquals(organizacion.getUbicacion(), ubicacion);
    assertEquals(organizacion.getTipo(), TipoOrganizacion.ASISTENCIA);
    assertEquals(organizacion.getCantidadEmpleados(), 10);
  }

  @Test
  public void organizacionSeRegistraAZonaNueva(){
    organizacion.registrarseAZona(zonaDeCobertura);

    verify(zonaDeCobertura).registrarOrganizacion(organizacion);
  }

  @Test
  public void organizacionRecibeNotificacionYLlamaLaFuncionalidadDeMuestra(){
    organizacion.recibirNotificacionMuestra(zonaDeCobertura, muestra);

    verify(funcionalidadMuestra).nuevoEvento(organizacion, zonaDeCobertura, muestra);
  }

  @Test
  public void organizacionRecibeNotificacionYLlamaLaFuncionalidadDeValidacion(){
    organizacion.recibirNotificacionValidacion(zonaDeCobertura, muestra);

    verify(funcionalidadValidacion).nuevoEvento(organizacion, zonaDeCobertura, muestra);
  }

  @Test
  public void organizacioncambiaFuncionalidadDeMuestra(){
    organizacion.cambiarFuncionalidadMuestra(funcionalidadMuestra2);
    organizacion.recibirNotificacionMuestra(zonaDeCobertura, muestra);

    verify(funcionalidadMuestra, never()).nuevoEvento(organizacion, zonaDeCobertura, muestra);
    verify(funcionalidadMuestra2).nuevoEvento(organizacion, zonaDeCobertura, muestra);
  }

  @Test
  public void organizacioncambiaFuncionalidadDeValidacion(){
    organizacion.cambiarFuncionalidadValidacion(funcionalidadValidacion2);
    organizacion.recibirNotificacionValidacion(zonaDeCobertura, muestra);

    verify(funcionalidadValidacion, never()).nuevoEvento(organizacion, zonaDeCobertura, muestra);
    verify(funcionalidadValidacion2).nuevoEvento(organizacion, zonaDeCobertura, muestra);
  }
}
