package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import modelo.Cotacao;
import modelo.Moeda;
import modelo.RespostaCotacao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Named
@ApplicationScoped
public class CotacaoService{

    private final HttpClient client;
    private final ObjectMapper mapper;


   public CotacaoService(){

       this.client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.NORMAL)
               .build();

       this.mapper = new ObjectMapper();

       this.mapper.registerModule(new JavaTimeModule());
   }


   public String buscarCotacao(Moeda moeda) throws Exception{

       HttpRequest request= HttpRequest.newBuilder()
               .uri(URI.create("https://api.frankfurter.app/latest?from="+ moeda.getCodigo() + "&to=BRL"))
               .GET()
               .build();


       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       if(response.statusCode() != 200){

           throw new RuntimeException("Erro na API!");
       }

       return response.body();
   }

   public String buscarCotacaoConversao(Moeda moedaOrigem, Moeda moedaDestino) throws IOException, InterruptedException {

       HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("https://api.frankfurter.app/latest?from=" +
                       moedaOrigem.getCodigo() + "&to=" + moedaDestino.getCodigo())).GET().build();

       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

       return response.body();
   }


   public RespostaCotacao buscarCotacaoObjeto(Moeda moeda) throws Exception{

       String json = buscarCotacao(moeda);

       return mapper.readValue(json, RespostaCotacao.class);
   }


   public Cotacao buscarCotacaoAtual(Moeda moeda) throws Exception{

       RespostaCotacao cotacaoAtual = buscarCotacaoObjeto(moeda);

       return new Cotacao(moeda, null, cotacaoAtual.getRates().get("BRL"), cotacaoAtual.getDate());
   }
}
