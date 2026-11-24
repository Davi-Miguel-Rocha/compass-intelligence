package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.Moeda;
import modelo.RespostaCotacao;
import pais.PaisEnum;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Named
@ApplicationScoped
public class ConversaoService{

    private CotacaoService cotacaoService;

    private final ObjectMapper mapper;

    @Inject
    public ConversaoService(CotacaoService cotacaoService) throws IOException, InterruptedException {

        this.cotacaoService = cotacaoService;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

  public BigDecimal buscarTaxa(Moeda moedaOrigem, Moeda moedaDestino) throws IOException, InterruptedException {

        String json = cotacaoService.buscarCotacaoConversao(moedaOrigem, moedaDestino);

        RespostaCotacao respostaCotacao = mapper.readValue(json, RespostaCotacao.class);

        return respostaCotacao.getRates().get(moedaDestino.getCodigo());
  }


  public BigDecimal conversao(Moeda moedaOrigem, Moeda moedaDestino, BigDecimal valor) throws IOException, InterruptedException {

        BigDecimal taxa = buscarTaxa(moedaOrigem, moedaDestino);

        return valor.multiply(taxa).setScale(2, RoundingMode.HALF_UP);

  }

   public void testar() throws IOException, InterruptedException {

       Moeda usd = new Moeda("Dólar americano", "USD", 1L, PaisEnum.ESTADOS_UNIDOS);
       Moeda eur = new Moeda("Euro", "EUR", 2L, PaisEnum.UNIAO_EUROPEIA);


       BigDecimal taxa = buscarTaxa(usd, eur);

       System.out.println("Taxa USD → EUR: " + taxa);
   }
}
