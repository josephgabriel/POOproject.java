import java.util.ArrayList;
import java.util.List;

public class ProdutoGerenciador {
    // Lista para armazenar todos os produtos cadastrados
    private List<Produto> produtos = new ArrayList<>();

    // Variável para controla o próximo ID a ser atribuído automaticamente
    private int proximoId = 1;

     //metodos para as funcionalidades que o professor especificou
  
    // inserir um novo produto na lista
    public void inserirProduto(String nome, double preco, int estoque) {
        Produto novo = new Produto(proximoId++, nome, preco, estoque);
        produtos.add(novo);
        System.out.println("Produto inserido com sucesso!");
    }

    // listar todos os produtos cadastrados
    public void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }
    }

    // atualizar os dados de um produto específico
    public void atualizarProduto(int id, String novoNome, double novoPreco, int novoEstoque) {
        Produto produto = encontrarProdutoPorId(id);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            produto.setEstoque(novoEstoque);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não foi localizado!");
        }
    }

    // remover um produto da lista utilizando o ID
    public void removerProduto(int id) {
        Produto produto = encontrarProdutoPorId(id);
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido com sucesso.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // buscar produtos que contenham parte do nome informado
    public void consultarPorNome(String nome) {
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum produto encontrado com esse nome!");
        }
    }

    // buscar produtos dentro de uma faixa de preço
    public void consultarPorFaixaDePreco(double precoMin, double precoMax) {
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getPreco() >= precoMin && p.getPreco() <= precoMax) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum produto na faixa de preço especificada!");
        }
    }

    // Método privado para encontrar um produto pelo ID
    private Produto encontrarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}

