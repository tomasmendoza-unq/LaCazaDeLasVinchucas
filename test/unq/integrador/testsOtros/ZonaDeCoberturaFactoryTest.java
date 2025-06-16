package unq.integrador.testsOtros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IBaseDeMuestras;
import unq.integrador.IUbicacion;
import unq.integrador.IZonaDeCobertura;
import unq.integrador.IZonaDeCoberturaFactory;
import unq.integrador.impls.ZonaDeCoberturaFactory;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ZonaDeCoberturaFactoryTest {

    IUbicacion epicentro;
    IZonaDeCoberturaFactory factory;
    IBaseDeMuestras baseDeMuestras;

    @BeforeEach
    public void setUp(){
        epicentro = mock(IUbicacion.class);
        baseDeMuestras = mock(IBaseDeMuestras.class);
        factory = new ZonaDeCoberturaFactory(baseDeMuestras);
    }

    @Test
    public void creaZonaYLaRegistraEnLaBase(){
        IZonaDeCobertura zona = factory.crearZona(epicentro, "Ezpeleta", 20d);

        verify(baseDeMuestras).RegistrarZona(zona);
    }

}
