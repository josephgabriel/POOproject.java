package DAO;

import classes.Cliente;
import Conexão.ConexãoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends ConexãoBD {

    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO tbClientes (CLI_NOME, CLI_EMAIL, CLI_ENDERECO, CLI_TELEFONE, CLI_SITUACAO) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setBoolean(5, cliente.isSituacao());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                cliente.setId(idGerado);
                System.out.println("Cliente cadastrado com ID: " + idGerado);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbClientes";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setEmail(rs.getString("CLI_EMAIL"));
                cliente.setEndereco(rs.getString("CLI_ENDERECO"));
                cliente.setTelefone(rs.getString("CLI_TELEFONE"));
                cliente.setSituacao(rs.getBoolean("CLI_SITUACAO"));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        return lista;
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE tbClientes SET CLI_NOME = ?, CLI_EMAIL = ?, CLI_ENDERECO = ?, CLI_TELEFONE = ?, CLI_SITUACAO = ? WHERE CLI_CODIGO = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setBoolean(5, cliente.isSituacao());
            stmt.setInt(6, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void deletarCliente(int id) {
        String sql = "DELETE FROM tbClientes WHERE CLI_CODIGO = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente: " + e.getMessage());
        }
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM tbClientes WHERE CLI_CODIGO = ?";
        Cliente cliente = null;

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setEmail(rs.getString("CLI_EMAIL"));
                cliente.setEndereco(rs.getString("CLI_ENDERECO"));
                cliente.setTelefone(rs.getString("CLI_TELEFONE"));
                cliente.setSituacao(rs.getBoolean("CLI_SITUACAO"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
        }

        return cliente;
    }
}
