
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
public class CotacaoService {

    private final HttpClient client;
    private final ObjectMapper mapper;

    public CotacaoService() {
        this.client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }

    // Consulta genérica: qualquer moeda de origem e destino
    public String buscarCotacao(Moeda moedaBase, Moeda moedaDestino)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://api.frankfurter.app/latest?from="
                                + moedaBase.getCodigo()
                                + "&to="
                                + moedaDestino.getCodigo()))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "Erro na API Frankfurter. Status HTTP: "
                            + response.statusCode()
            );
        }

        return response.body();
    }

    // Mantém a compatibilidade com consultas direcionadas ao BRL
    public String buscarCotacao(Moeda moeda)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://api.frankfurter.app/latest?from="
                                + moeda.getCodigo()
                                + "&to=BRL"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "Erro na API Frankfurter. Status HTTP: "
                            + response.statusCode()
            );
        }

        return response.body();
    }

    // Mantém a assinatura utilizada pelo ConversaoService
    public String buscarCotacaoConversao(
            Moeda moedaOrigem,
            Moeda moedaDestino
    ) throws IOException, InterruptedException {

        return buscarCotacao(moedaOrigem, moedaDestino);
    }

    public RespostaCotacao buscarCotacaoObjeto(Moeda moeda)
            throws Exception {

        String json = buscarCotacao(moeda);

        return mapper.readValue(json, RespostaCotacao.class);
    }

    // Nova versão: permite informar origem e destino.
    // se você quiser converter dolar para euro será o preço do dolar frente ao euro.

    public Cotacao buscarCotacaoAtual(
            Moeda moedaBase,
            Moeda moedaDestino
    ) throws Exception {

        String json = buscarCotacao(moedaBase, moedaDestino);

        RespostaCotacao resposta =
                mapper.readValue(json, RespostaCotacao.class);

        return new Cotacao(
                moedaBase,
                moedaDestino,
                resposta.getRates().get(moedaDestino.getCodigo()),
                resposta.getDate()
        );
    }

    // Método antigo preservado temporariamente devido a receios meus de quebrar algo.
    public Cotacao buscarCotacaoAtual(Moeda moeda)
            throws Exception {

        RespostaCotacao cotacaoAtual = buscarCotacaoObjeto(moeda);

        return new Cotacao(
                moeda,
                null,
                cotacaoAtual.getRates().get("BRL"),
                cotacaoAtual.getDate()
        );
    }
}
