package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.Moeda;
import pais.PaisEnum;
import service.ConversaoService;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named("conversaoBean")
@ViewScoped
public class ConversaoBean implements Serializable {

    private Moeda moedaOrigem;

    private  Moeda moedaDestino;

    private BigDecimal valor;

    private BigDecimal resultado;

    private List<Moeda> moedas;

    @Inject
   private ConversaoBean conversaoBean;
    @Named
    @Inject
    private ConversaoService conversaoService;

    @PostConstruct
    public void init(){

       moedas = new ArrayList<Moeda>();

       moedas.add(new Moeda("Dólar americano","USD", 1L, PaisEnum.ESTADOS_UNIDOS));

       moedas.add(new Moeda("Euro","EUR",2L,PaisEnum.UNIAO_EUROPEIA));



    }


    public void converter() {

        if (valor == null || moedaOrigem == null || moedaDestino == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN,
                            "Atenção",
                            "Preencha o valor e selecione as duas moedas."
                    ));
            return;
        }

        if (conversaoService.moedaIsEquals(moedaOrigem, moedaDestino)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN,
                            "Atenção",
                            "Escolha moedas diferentes."
                    ));
            return;
        }

        try {
            resultado = conversaoService.conversao(
                    moedaOrigem, moedaDestino, valor
            );

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Conversão realizada!",
                            "A cotação foi consultada com sucesso."
                    ));

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Erro",
                            "A consulta foi interrompida."
                    ));

        } catch (Exception e) {
            e.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Erro na conversão",
                            e.getMessage() != null ? e.getMessage() : "Não foi possível realizar a conversão."
                    ));
        }
    }


    public Moeda getMoedaOrigem() {
        return moedaOrigem;
    }

    public void setMoedaOrigem(Moeda moedaOrigem) {
        this.moedaOrigem = moedaOrigem;
    }

    public Moeda getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(Moeda moedaDestino) {
        this.moedaDestino = moedaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public List<Moeda> getMoedas() {
        return moedas;
    }

    public void setMoedas(List<Moeda> moedas) {
        this.moedas = moedas;
    }
}
