package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.Cotacao;
import modelo.Moeda;
import pais.PaisEnum;
import service.CotacaoService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("monitoramentoBean")
@ViewScoped
public class MonitoramentoBean implements Serializable {

    @Inject
    CotacaoService cotacaoService;

    private List<Cotacao> cotacoes;

    @PostConstruct
    public void init() throws Exception {

        cotacoes = new ArrayList<>();

        Moeda dolar = new Moeda("Dólar americano","USD", 1L, PaisEnum.ESTADOS_UNIDOS);

        Moeda euro = new Moeda("Euro","EUR", 2L, PaisEnum.UNIAO_EUROPEIA);

        Moeda yuan = new Moeda("Yuan","CNY", 3L, PaisEnum.CHINA);

        Moeda dolar_canadense = new Moeda("Dólar canadense","CAD", 4L, PaisEnum.CANADA);

        Moeda won_sul_coreano = new Moeda("Won sul-coreano", "KRW", 5L, PaisEnum.COREIA_SUL);


        cotacoes.add(cotacaoService.buscarCotacaoAtual(dolar));
        cotacoes.add(cotacaoService.buscarCotacaoAtual(euro));
        cotacoes.add(cotacaoService.buscarCotacaoAtual(yuan));
        cotacoes.add(cotacaoService.buscarCotacaoAtual(dolar_canadense));
        cotacoes.add(cotacaoService.buscarCotacaoAtual(won_sul_coreano));



    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }


}
