package DAO;

import Conexão.ConexãoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends ConexãoBD {

    // Inserir novo produto
    public void inserir(Produto produto) {
        String sql = "INSERT INTO tbProdutos (PRO_NOME, PRO_DESCRICAO, PRO_PRECO, PRO_ESTOQUE) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao inserir produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Atualizar produto existente
    public void atualizar(Produto produto) {
        String sql = "UPDATE tbProdutos SET PRO_NOME = ?, PRO_DESCRICAO = ?, PRO_PRECO = ?, PRO_ESTOQUE = ? WHERE PRO_CODIGO = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, produto.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Deletar produto por ID
    public void deletar(int id) {
        String sql = "DELETE FROM tbProdutos WHERE PRO_CODIGO = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao deletar produto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Buscar produto por ID
    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM tbProdutos WHERE PRO_CODIGO = ?";
        Produto produto = null;

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getInt("PRO_CODIGO"),
                    rs.getString("PRO_NOME"),
                    rs.getString("PRO_DESCRICAO"),
                    rs.getDouble("PRO_PRECO"),
                    rs.getInt("PRO_ESTOQUE")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
            e.printStackTrace();
        }

        return produto;
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM tbProdutos";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("PRO_CODIGO"),
                    rs.getString("PRO_NOME"),
                    rs.getString("PRO_DESCRICAO"),
                    rs.getDouble("PRO_PRECO"),
                    rs.getInt("PRO_ESTOQUE")
                );
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            e.printStackTrace();
        }

        return produtos;
    }
}
