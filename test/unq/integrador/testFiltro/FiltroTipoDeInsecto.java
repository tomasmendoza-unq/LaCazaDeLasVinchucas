package unq.integrador.testFiltro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unq.integrador.IFiltroMuestra;
import unq.integrador.IMuestra;

import static org.mockito.Mockito.mock;

public class FiltroTipoDeInsecto {

    IFiltroMuestra filtro;
    IMuestra muestra;

    @BeforeEach
    public void setUp(){
        muestra = mock(IMuestra.class);
    }

    
}
