package factory;

import java.sql.*;

public class ConexaoBD {
    private static String sqlUrl = "jdbc:sqlserver://GustavoRV;Database=BDUrbanShoes;IntegratedSecurity=true;" + "encrypt=true;trustServerCertificate=true" ;

    public int atualizarTabela(String sql){ //recebe uma string com um código de SQL, e serve tanto para adicionar dados no banco de dados, quanto para modificar os dados
        try(Connection con = DriverManager.getConnection(sqlUrl)){ //conexão
            System.out.println("Conexão bem sucedida!");
            Statement consulta = con.createStatement(); //objeto pra executar o código SQL
            int resultado = consulta.executeUpdate(sql); //executando a consulta no SQL, que retorna a quantidade de linhas afetadas
            con.close();
            return resultado; //retornando a quantidade de linhas afetadas
        }catch(SQLException e) { //tratamento de erro no SQL
            System.out.println("ERRO!");
            e.printStackTrace();
            return -1;
        }
    }
    
    public static Connection createConnection() {
    		
    	try {
    		Connection conn = DriverManager.getConnection(sqlUrl);
    		return conn;
    	} catch (Exception e) {	
    		e.printStackTrace();
    		return null;
    	}
    }
}
