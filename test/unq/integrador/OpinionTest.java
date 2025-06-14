package unq.integrador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpinionTest {
    private Opinion op;
    private TipoOpinion tp;
    
    @BeforeEach
    public void setUp() {
        tp = TipoOpinion.NINGUNA;
        op = new Opinion(tp);
    }

    @Test
    public void testGettersDeOpinion() {
        LocalDate fecha = LocalDate.now();
        assertEquals(fecha, op.getFechaDeCreacion());
        
        assertEquals(TipoOpinion.NINGUNA, op.getTipo());
        
        assertEquals("Ninguna", op.imprimirTipo());
    }

}
