package school.sptech.projetoindividual;

public class Jogo {

    private Integer id;
    private String nome;
    private Double preco;
    private String dataLancamento;
    private Integer categoriaId;
    private String categoriaNome;
    private String plataforma;
    private boolean multiplayer;

    public Jogo(Integer id, String nome, Double preco, String dataLancamento, Integer categoriaId, String categoriaNome, String plataforma, boolean multiplayer) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dataLancamento = dataLancamento;
        this.categoriaId = categoriaId;
        this.categoriaNome = categoriaNome;
        this.plataforma = plataforma;
        this.multiplayer = multiplayer;
    }

    public Jogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }
}