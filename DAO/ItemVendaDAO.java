package DAO;

import Conexão.ConexãoBD;
import classes.ItemVenda;

import java.sql.*;

public class ItemVendaDAO extends ConexãoBD {
    public void salvar(ItemVenda itemVenda){
        try(Connection con = DriverManager.getConnection(sqlUrl)){
            String sql = "INSERT INTO tbItensVenda (VEN_CODIGO, PRO_CODIGO,IVE_QTDE, IVE_PRECO_UNIT) VALUES(?,?,?,?)";
            //"INSERT INTO tbItensVenda (IVE_CODIGO, VEN_CODIGO, PRO_CODIGO,IVE_QTDE, IVE_PRECO_UNIT) VALUES(?,?,?,?,?)"
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, itemVenda.getId());
            ps.setInt(1, itemVenda.getVenda().getId());
            ps.setInt(2, itemVenda.getProduto().getId());
            ps.setInt(3, itemVenda.getQuantidade());
            ps.setDouble(4, itemVenda.getPrecoUnitario());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

