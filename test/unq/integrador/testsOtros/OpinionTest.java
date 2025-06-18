package unq.integrador.testsOtros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unq.integrador.impls.Opinion;
import unq.integrador.enums.TipoOpinion;

public class OpinionTest {
    private Opinion op;
    private TipoOpinion tp;
    
    @BeforeEach
    public void setUp() {
        tp = TipoOpinion.NINGUNA;
        op = new Opinion(tp, 2);
    }

    @Test
    public void testGettersDeOpinion() {
        LocalDate fecha = LocalDate.now();
        assertEquals(fecha, op.getFechaDeCreacion());
        
        assertEquals(TipoOpinion.NINGUNA, op.getTipo());
        
        assertEquals("Ninguna", op.imprimirTipo());
        assertEquals(2, op.getID());
    }

}
