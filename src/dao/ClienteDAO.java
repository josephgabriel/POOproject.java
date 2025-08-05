package DAO;

import model.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private static final String url = "jdbc:sqlserver://GustavoRV;Database=BDUrbanShoes;IntegratedSecurity=true;encrypt=true;trustServerCertificate=true";

    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO tbClientes (CLI_NOME, CLI_EMAIL, CLI_ENDERECO, CLI_TELEFONE) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir cliente", e);
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbClientes";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setEmail(rs.getString("CLI_EMAIL"));
                cliente.setEndereco(rs.getString("CLI_ENDERECO"));
                cliente.setTelefone(rs.getString("CLI_TELEFONE"));
                lista.add(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }

        return lista;
    }

    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE tbClientes SET CLI_NOME = ?, CLI_EMAIL = ?, CLI_ENDERECO = ?, CLI_TELEFONE = ? WHERE CLI_CODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    public void deletarCliente(int id) {
        String sql = "DELETE FROM tbClientes WHERE CLI_CODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cliente", e);
        }
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM tbClientes WHERE CLI_CODIGO = ?";
        Cliente cliente = null;

        try (Connection conn = DriverManager.getConnection(url);
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
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        }

        return cliente;
    }
}