package pais;

public enum PaisEnum {

    ESTADOS_UNIDOS("USD", "Estados unidos", "USA.png"),
    UNIAO_EUROPEIA("EUR", "União europeia", "EUR.png"),
    CHINA("CNY","China","china.png"),
    CANADA("CAD","Canadá","canada.jpg"),
    COREIA_SUL("KRW","Won sul-coreano","coreiaSul.png"),
    BRASIL("BRL","Real","brasil.png"),
    JAPAN("JPY","Iene japonês","japao.png");


    private final String codigoMoeda;

   private final String nome;

   private final String imagem;

    PaisEnum(String codigoMoeda, String nome, String imagem) {
        this.codigoMoeda = codigoMoeda;
        this.nome = nome;
        this.imagem = imagem;
    }

    public String getCodigoMoeda() {
        return codigoMoeda;
    }

    public String getNome() {
        return nome;
    }

    public String getImagem() {
        return imagem;
    }
}
