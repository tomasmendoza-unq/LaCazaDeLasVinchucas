package unq.integrador.testOrganizacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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
  private IFuncionalidadExterna funcionalidadValidacion;
  
  @BeforeEach
  public void setUp(){
    ubicacion = mock(IUbicacion.class);

    funcionalidadMuestra = mock(IFuncionalidadExterna.class);
    funcionalidadValidacion = mock(IFuncionalidadExterna.class);

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
}
