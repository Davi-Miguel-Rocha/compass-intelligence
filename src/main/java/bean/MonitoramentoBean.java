package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.Cotacao;
import modelo.Moeda;
import service.CotacaoService;
import service.MoedaService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("monitoramentoBean")
@ViewScoped
public class MonitoramentoBean implements Serializable {

    @Inject
    CotacaoService cotacaoService;

    @Inject
    MoedaService moedaService;

    private List<Cotacao> cotacoes;

    @PostConstruct
    public void init() throws Exception {

        cotacoes = new ArrayList<>();

        List<Moeda> moedas = moedaService.listarMoedas();

        Moeda dolar = moedas.stream()
                .filter(m -> m.getCodigo().equals("USD"))
                .findFirst()
                .orElseThrow();

        for (Moeda moeda : moedas) {

            if (moeda.getCodigo().equals("USD")) {
                continue;
            }

            cotacoes.add(
                    cotacaoService.buscarCotacaoAtual(moeda, dolar)
            );
        }
    }

    public List<Cotacao> getCotacoes() {
        return cotacoes;
    }


}
