package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jacson
 */

public class ConexaoDB {

    public Statement stm;
    
    public ResultSet rs;
    
    private String driver = "org.mysql.Driver";
    
    private String caminho = "jdbc:mysql://localhost:3306/hotel";
    
    private String usuario = "root";
    
    private String senha = "root";
    
    public Connection con;
    
    public void conectar() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            con = DriverManager.getConnection(caminho,usuario,senha);
            
            //JOptionPane.showMessageDialog(null, "Show!!");
            
        } catch (SQLException exception) {
            //JOptionPane.showMessageDialog(null, "Erro:"+exception.getMessage());
        }
    }
    
    public void executeSql(String sql) {
        try {
            stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL"+ exception.getMessage());
        }
    }

    public void executeSql(PreparedStatement ps) {
        try {
            rs = ps.executeQuery();
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL"+ exception.getMessage());
        }
    }
        
    public void desconectarConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
