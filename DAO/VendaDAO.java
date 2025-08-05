package DAO;

import Conexão.ConexãoBD;
import classes.Cliente;
import classes.ItemVenda;
import classes.Produto;
import classes.Venda;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO extends ConexãoBD {

    public void salvarVenda(Venda venda) {
        String sql = "INSERT INTO tbVendas (CLI_CODIGO, VEN_DATA) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(sqlUrl)){
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, venda.getCliente().getId()); // falta criar o getid do cliente
            ps.setTimestamp(2, venda.getDataVenda());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    venda.setId(rs.getInt(1));

                }
            }
            /*
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                venda.setId(rs.getInt(1));
            }
             */

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar venda", e);
        }
    }

    public List<Venda> listarVendasCompletas() {
        List<Venda> lista = new ArrayList<>();

        String sql = """
        SELECT 
            v.VEN_CODIGO, v.VEN_DATA,
            c.CLI_CODIGO, c.CLI_NOME, c.CLI_EMAIL, c.CLI_ENDERECO, c.CLI_TELEFONE,
            p.PRO_CODIGO, p.PRO_NOME, p.PRO_DESCRICAO, p.PRO_PRECO,
            iv.IVE_QTDE, iv.IVE_PRECO_UNIT
        FROM tbVendas v
        INNER JOIN tbClientes c ON v.CLI_CODIGO = c.CLI_CODIGO
        INNER JOIN tbItensVenda iv ON v.VEN_CODIGO = iv.VEN_CODIGO
        INNER JOIN tbProdutos p ON iv.PRO_CODIGO = p.PRO_CODIGO
        """;

        try (Connection con = DriverManager.getConnection(sqlUrl)){
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // ainda falta os gettes e settes da classe cliente
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("CLI_CODIGO"));
                cliente.setNome(rs.getString("CLI_NOME"));
                cliente.setEmail(rs.getString("CLI_EMAIL"));
                cliente.setEndereco(rs.getString("CLI_ENDERECO"));
                cliente.setTelefone(rs.getString("CLI_TELEFONE"));

                Produto produto = new Produto();
                produto.setId(rs.getInt("PRO_CODIGO"));
                produto.setNome(rs.getString("PRO_NOME"));
                produto.setDescricao(rs.getString("PRO_DESCRICAO")); // falta criar a descrição do cliente
                produto.setPreco(rs.getDouble("PRO_PRECO"));

                ItemVenda item = new ItemVenda();
                item.setProduto(produto);
                item.setQuantidade(rs.getInt("IVE_QTDE"));
                item.setPrecoUnitario(rs.getDouble("IVE_PRECO_UNIT"));

                Venda venda = new Venda();
                venda.setId(rs.getInt("VEN_CODIGO"));
                venda.setDataVenda(rs.getTimestamp("VEN_DATA"));
                venda.setCliente(cliente);
                venda.getItens().add(item);

                lista.add(venda);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar vendas completas", e);
        }

        return lista;
    }
}