package bean;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import modelo.Moeda;
import pais.PaisEnum;

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

    @PostConstruct
    public void init(){

       moedas = new ArrayList<Moeda>();

       moedas.add(new Moeda("Dólar americano","USD", 1L, PaisEnum.ESTADOS_UNIDOS));

       moedas.add(new Moeda("Euro","EUR",2L,PaisEnum.UNIAO_EUROPEIA));



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
