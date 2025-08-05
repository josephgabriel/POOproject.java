// Classe para representar um produto do sistema Netshoe
package model;

public class Produto {
    // Atributos privados do produto (encapsulamento)
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int estoque;

    // inicializar os atributos ao criar um novo produto
    public Produto(int id, String nome, String descricao, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    public Produto() {}

    // leitura dos atributos com get
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    // Métodos de modificação para alterar atributos com set
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    // Método toString sobrescrito para exibir o produto de forma legivel ao user
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Preço: R$%.2f | Estoque: %d",
                             id, nome, preco, estoque);
    }
}
