package unq.integrador.testsOtros;

import org.junit.jupiter.api.BeforeEach;
import unq.integrador.IBaseDeMuestras;
import unq.integrador.IMuestra;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.impls.BaseDeMuestras;

public class BaseDeDatosTest {

    IBaseDeMuestras baseDeMuestras;
    IMuestra Muestra;
    IUbicacion ubicacionMuestra;
    IZonaDeCobertura zonaDeCobertura;

    @BeforeEach
    public void setUp(){
        baseDeMuestras = new BaseDeMuestras();
    }
}
