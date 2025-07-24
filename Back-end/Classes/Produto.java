// Classe para representar um produto do sistema Netshoe
public class Produto {
    // Atributos privados do produto
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    // inicializar os atributos ao criar um novo produto
    public Produto(int id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    // leitura dos atributos
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }

    // Métodos de modificação para alterar atributos
    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(double preco) { this.preco = preco; }
    public void setEstoque(int estoque) { this.estoque = estoque; }

    // Método toString sobrescrito para exibir o produto de forma legivel ao user
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Preço: R$%.2f | Estoque: %d",
                             id, nome, preco, estoque);
    }
}
