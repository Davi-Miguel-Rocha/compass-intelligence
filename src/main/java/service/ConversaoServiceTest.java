package service;


import modelo.Moeda;
import org.junit.Test;
import pais.PaisEnum;

import java.io.IOException;
import java.math.BigDecimal;

public class ConversaoServiceTest {

    @Test
    public void deveBuscarTaxa() throws IOException, InterruptedException {

        CotacaoService cotacaoService = new CotacaoService();



        Moeda usd = new Moeda("Dólar americano", "USD", 1L, PaisEnum.ESTADOS_UNIDOS);
        Moeda eur = new Moeda("Euro", "EUR", 2L, PaisEnum.UNIAO_EUROPEIA);




        System.out.println("Taxa USD → EUR: ");
    }
}
