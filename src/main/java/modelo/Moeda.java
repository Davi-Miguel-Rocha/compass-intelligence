package modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import pais.PaisEnum;

public class Moeda {


    private String name;

    private String codigo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private PaisEnum pais;



    public Moeda(String name, String codigo, Long id, PaisEnum pais) {
        this.name = name;
        this.codigo = codigo;
        this.id = id;
        this.pais = pais;

    }

    public Moeda(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public PaisEnum getPais() {
        return pais;
    }
}
