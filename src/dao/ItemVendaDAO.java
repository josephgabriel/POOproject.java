package dao;

import factory.ConexaoBD;
import model.ItemVenda;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemVendaDAO extends ConexaoBD {
    public void salvar(ItemVenda itemVenda){
        try{
            String sqlUrl = "INSERT INTO tbItensVenda (IVE_CODIGO, VEN_CODIGO, PRO_CODIGO,IVE_QTDE, IVE_PRECO_UNIT) VALUES(?,?,?,?,?)";
            PreparedStatement ps = null;
            ps = (PreparedStatement) DriverManager.getConnection(sqlUrl);
            ps.setInt(1, itemVenda.getId());
            ps.setInt(2, itemVenda.getVenda().getId());
            ps.setInt(3, itemVenda.getProduto().getId());
            ps.setInt(4, itemVenda.getQuantidade());
            ps.setDouble(5, itemVenda.getPrecoUnitario());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


