public abstract class Espaco {
    // --- atributos comuns (privados, encapsulados) ---
    private String id;
    private String nome;
    private String localizacao;
    private int capacidade;

    // --- construtor (serve para criar o objeto já preenchido) ---
    public Espaco(String id, String nome, String localizacao, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.capacidade = capacidade;
    }

    // --- getters (métodos de leitura dos atributos) ---
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
