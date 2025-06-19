package unq.integrador.testUbicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.impls.Ubicacion;

public class UbicacionTest {
  private IUbicacion ubicacion1;
  private IUbicacion ubicacion2;
  private IUbicacion ubicacion3;
  private IUbicacion ubicacion4;
  private IUbicacion ubicacion5;
  
  private IMuestra muestra1;
  private IMuestra muestra2;
  private IMuestra muestra3;
  private IMuestra muestra4;

  @BeforeEach
  public void setUp() {
    ubicacion1 = new Ubicacion(10, 20);
    ubicacion2 = new Ubicacion(11, 21);
    ubicacion3 = new Ubicacion(12, 22);
    ubicacion4 = new Ubicacion(13, 23);
    ubicacion5 = new Ubicacion(14, 24);

    muestra1 = mock(IMuestra.class);
    muestra2 = mock(IMuestra.class);
    muestra3 = mock(IMuestra.class);
    muestra4 = mock(IMuestra.class);
  }

  @Test
  public void testGetters(){
    assertEquals(ubicacion1.getLatitud(), 10);
    assertEquals(ubicacion1.getLongitud(), 20);
  }

  @Test
  public void testDistanciaUbicacion(){
    assertEquals(ubicacion1.distanciaA(ubicacion2), 155,941215);
    assertEquals(ubicacion1.distanciaA(ubicacion1), 0);
  }

  @Test
  public void hayUbicacionesAMenosDistanciaDeLaDada(){
    List<IUbicacion> ubicaciones = Arrays.asList(ubicacion2, ubicacion3, ubicacion4, ubicacion5);

    assertTrue(ubicacion1.ubicacionesAMenosDe(ubicaciones, 400).contains(ubicacion2));
    assertTrue(ubicacion1.ubicacionesAMenosDe(ubicaciones, 400).contains(ubicacion3));
    assertFalse(ubicacion1.ubicacionesAMenosDe(ubicaciones, 400).contains(ubicacion4));
    assertFalse(ubicacion1.ubicacionesAMenosDe(ubicaciones, 400).contains(ubicacion5));
  }

  @Test
  public void noHayUbicacionesaMenosDistanciaDeLaDada(){
    List<IUbicacion> ubicaciones = Arrays.asList(ubicacion2, ubicacion3, ubicacion4, ubicacion5);

    assertTrue(ubicacion1.ubicacionesAMenosDe(ubicaciones, 100).isEmpty());
  }

  @Test
  public void hayMuestrasAMenosDistanciaDeLaDada(){
    when(muestra1.getUbicacion()).thenReturn(ubicacion2);
    when(muestra2.getUbicacion()).thenReturn(ubicacion3);
    when(muestra3.getUbicacion()).thenReturn(ubicacion4);
    when(muestra4.getUbicacion()).thenReturn(ubicacion5);

    List<IMuestra> muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);

    assertTrue(ubicacion1.muestrasAMenosDe(muestras, 400).contains(muestra1));
    assertTrue(ubicacion1.muestrasAMenosDe(muestras, 400).contains(muestra2));
    assertFalse(ubicacion1.muestrasAMenosDe(muestras, 400).contains(muestra3));
    assertFalse(ubicacion1.muestrasAMenosDe(muestras, 400).contains(muestra4));
  }

  @Test
  public void noHayMuestrasAMenosDistanciaDeLaDada(){
    when(muestra1.getUbicacion()).thenReturn(ubicacion2);
    when(muestra2.getUbicacion()).thenReturn(ubicacion3);
    when(muestra3.getUbicacion()).thenReturn(ubicacion4);
    when(muestra4.getUbicacion()).thenReturn(ubicacion5);

    List<IMuestra> muestras = Arrays.asList(muestra1, muestra2, muestra3, muestra4);

    assertTrue(ubicacion1.muestrasAMenosDe(muestras, 100).isEmpty());
  }
}
