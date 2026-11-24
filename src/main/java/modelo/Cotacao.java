package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Cotacao {

    private Moeda moedaBase;

    private Moeda moedaDestino;

    private BigDecimal taxa;

    private LocalDate data;

    public Cotacao(Moeda moedaBase, Moeda moedaDestino, BigDecimal taxa, LocalDate data) {
        this.moedaBase = moedaBase;
        this.moedaDestino = moedaDestino;
        this.taxa = taxa;
        this.data = data;
    }

    public Cotacao(){}

    public Moeda getMoedaBase() {
        return moedaBase;
    }

    public void setMoedaBase(Moeda moedaBase) {
        this.moedaBase = moedaBase;
    }

    public Moeda getMoedaDestino() {
        return moedaDestino;
    }

    public void setMoedaDestino(Moeda moedaDestino) {
        this.moedaDestino = moedaDestino;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getData() {
        return data;
    }
}
