package model;

import DAO.ProdutoDAO;
import java.util.List;

public class ProdutoGerenciador {
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    // Inserir novo produto
    public void inserirProduto(String nome, String descricao, double preco, int estoque) {
        Produto novo = new Produto(0, nome, descricao, preco, estoque); // ID será gerado no banco
        produtoDAO.inserir(novo);
        System.out.println("Produto inserido com sucesso no banco!");
    }

    // Listar todos os produtos do banco
    public void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarTodos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado no banco.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p);
            }
        }
    }

    // Atualizar produto
    public void atualizarProduto(int id, String novoNome, double novoPreco, int novoEstoque) {
        Produto produto = produtoDAO.buscarPorId(id);
        if (produto != null) {
            produto.setNome(novoNome);
            produto.setPreco(novoPreco);
            produto.setEstoque(novoEstoque);
            produtoDAO.atualizar(produto);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não foi localizado no banco!");
        }
    }

    // Remover produto
    public void removerProduto(int id) {
        Produto produto = produtoDAO.buscarPorId(id);
        if (produto != null) {
            produtoDAO.deletar(id);
            System.out.println("Produto removido com sucesso do banco.");
        } else {
            System.out.println("Produto não encontrado no banco.");
        }
    }

    // Buscar por nome (parcial)
    public void consultarPorNome(String nome) {
        List<Produto> produtos = produtoDAO.listarTodos();
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

    // Buscar por faixa de preço
    public void consultarPorFaixaDePreco(double precoMin, double precoMax) {
        List<Produto> produtos = produtoDAO.listarTodos();
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
}
