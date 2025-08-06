import java.sql.*;

public class ConexãoBD {
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

/*

    método de exemplo de como fazer as consultas:

    public void consultarClientes(String tabela){ //vai mostrar todas as linhas que a consulta encontrar
        String sql = "SELECT * FROM " + tabela;
        try(Connection con = DriverManager.getConnection(sqlUrl)){ //conexão
            System.out.println("Conexão bem sucedida!");
            PreparedStatement consulta = con.prepareStatement(sql);
            ResultSet linhas = consulta.executeQuery();
            while(linhas.next()){ //isso faz com que linhas pegue todas as informações da próxima linha,e isso é executado a cada loop OBS: a primeira posição sempre é NULL, logo, precisa sempre fazer esse comando pelo menos 1 vez
                String nome = linhas.getString("cli_nome");
                String email = linhas.getString("cli_email");
                String telefone = linhas.getString("cli_telefone");
                String endereco = linhas.getString("cli_endereco");
                System.out.println(nome+' '+telefone+' '+email+' '+endereco);
            }
            con.close();//fechando conexão
        }catch(SQLException e){ //tratamento de erros
            System.out.println("ERRO!");
            e.printStackTrace();
        }
    }


    public void consultarClientes(String tabela, String condicoes){ //vai mostrar todas as linhas que a consulta encontrar
        String sql = "SELECT * FROM " + tabela + " WHERE " + condicoes;
        try(Connection con = DriverManager.getConnection(sqlUrl)){ //conexão
            System.out.println("Conexão bem sucedida!");
            PreparedStatement consulta = con.prepareStatement(sql);
            ResultSet linhas = consulta.executeQuery();
            while(linhas.next()){ //isso faz com que linhas pegue todas as informações da próxima linha,e isso é executado a cada loop OBS: a primeira posição sempre é NULL, logo, precisa sempre fazer esse comando pelo menos 1 vez
                String nome = linhas.getString("cli_nome");
                String email = linhas.getString("cli_email");
                String telefone = linhas.getString("cli_telefone");
                String endereco = linhas.getString("cli_endereco");
                System.out.println(nome+' '+telefone+' '+email+' '+endereco);
            }
            con.close();//fechando conexão
        }catch(SQLException e){ //tratamento de erros
            System.out.println("ERRO!");
            e.printStackTrace();
        }
    }

 */
}
