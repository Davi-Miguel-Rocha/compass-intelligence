package service;


import jakarta.enterprise.context.ApplicationScoped;
import modelo.Moeda;
import pais.PaisEnum;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MoedaService {


    public List<Moeda> listarMoedas(){

        List<Moeda> moedas = new ArrayList<>();

        moedas.add(new Moeda("Dólar americano","USD", 1L, PaisEnum.ESTADOS_UNIDOS));

        moedas.add(new Moeda("Euro","EUR", 2L, PaisEnum.UNIAO_EUROPEIA));

        moedas.add(new Moeda("Yuan","CNY", 3L, PaisEnum.CHINA));

        moedas.add(new Moeda("Dólar canadense","CAD", 4L, PaisEnum.CANADA));

        moedas.add(new Moeda("Won sul-coreano", "KRW", 5L, PaisEnum.COREIA_SUL));

        moedas.add(new Moeda("Real Brasileiro", "BRL", 6L, PaisEnum.BRASIL));

        moedas.add(new Moeda("Iene japonês", "JPY", 7L, PaisEnum.JAPAN));

        return moedas;

    }



}
