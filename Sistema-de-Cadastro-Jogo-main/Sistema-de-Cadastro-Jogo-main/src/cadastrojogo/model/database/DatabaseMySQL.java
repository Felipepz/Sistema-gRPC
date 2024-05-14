/* package cadastrojogo.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMySQL {
    
    private static Connection conexao = null;
    
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/cursojdbc";
                String usuario = "root";
                String senha = "root";
                conexao = DriverManager.getConnection(url, usuario, senha);
                System.out.println("Conexão realizada com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Erro ao conectar com o banco de dados: " + ex.getMessage());
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão fechada com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão com o banco de dados: " + ex.getMessage());
            }
        }
    }
    
}

*/