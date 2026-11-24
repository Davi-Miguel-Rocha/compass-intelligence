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

    @Inject
    private CotacaoService cotacaoService;

    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public BigDecimal buscarTaxa(Moeda moedaOrigem, Moeda moedaDestino)
            throws IOException, InterruptedException {

        String json = cotacaoService.buscarCotacaoConversao(moedaOrigem, moedaDestino);

        RespostaCotacao respostaCotacao = mapper.readValue(json, RespostaCotacao.class);

       BigDecimal taxa = respostaCotacao.getRates().get(moedaDestino.getCodigo());

       if(taxa == null){

           throw new IllegalArgumentException("Desculpe, não encontramos a cotação da moeda destino!");
       }

       return taxa;
   }

   public Boolean moedaIsEquals(Moeda moedaOrigem, Moeda moedaDestino){

        if(moedaOrigem != null && moedaDestino != null){

            return moedaOrigem.getCodigo().equals(moedaDestino.getCodigo());
        }

        return false;
   }


  public BigDecimal conversao(Moeda moedaOrigem, Moeda moedaDestino, BigDecimal valor) throws IOException, InterruptedException {

        BigDecimal taxa = buscarTaxa(moedaOrigem, moedaDestino);

        BigDecimal converter = valor.multiply(taxa).setScale(2, RoundingMode.HALF_UP);

        return converter;

  }

}
