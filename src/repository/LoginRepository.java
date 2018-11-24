/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import database.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Funcionario;
import model.Login;

/**
 *
 * @author jacson
 */
public class LoginRepository {
    ConexaoDB cone = new ConexaoDB();
    private FuncionarioRepository funcionarioRepository;
    //Login login = new Login();

    public LoginRepository(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public void salvar(Login login) {
        cone.conectar();

        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "login (usuario, senha, funcionario) VALUES(?,?,?)");
            
            String senha = this.MD5(login.getSenha());
            
            ps.setString(1, login.getUsuario());
            ps.setString(2, senha);
            ps.setInt(3, login.getFuncionario().getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Login Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Login!");
        }

        cone.desconectarConexao();
    }
    
    public void editar(Login login) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE login "
                    + "SET usuario = ?, senha = ?, funcionario = ? WHERE id = ?");
            
            String senha = this.MD5(login.getSenha());
            
            ps.setString(1, login.getUsuario());
            ps.setString(2, senha);
            ps.setInt(3, login.getFuncionario().getId());
            ps.setInt(4, login.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Login Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Login!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Login login) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM login WHERE id = ?");
            ps.setInt(1, login.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Login Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Login!");
        }
        
        cone.desconectarConexao();
    }
    
    public Login buscar(int id) {
        cone.conectar();
        Login login = new Login();
        cone.executeSql("SELECT * FROM login WHERE id = "+ id);
        try{
            cone.rs.first();
            login.setId(cone.rs.getInt("id"));
            login.setUsuario(cone.rs.getString("usuario"));
            login.setSenha(cone.rs.getString("senha"));
            
            Funcionario funcionario = this.funcionarioRepository.buscar(cone.rs.getInt("funcionario"));
            login.setFuncionario(funcionario);
            
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Login não encontrado!");
        }
        
        cone.desconectarConexao();
        return login;
    }
    
    public String MD5(String md5) {
       try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
              sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
           }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public boolean autenticar(String usuario, String senha) {
        cone.conectar();
        Login login = new Login();
        
        senha = this.MD5(senha);
        
        try{
            PreparedStatement ps = cone.con.prepareStatement("SELECT * FROM login WHERE usuario = ? AND senha = ? AND ativo = 1");
            ps.setString(1, usuario);
            ps.setString(2, senha);
            
            cone.executeSql(ps);
            cone.rs.first();
            
            login.setId(cone.rs.getInt("id"));
            login.setUsuario(cone.rs.getString("usuario"));
            login.setSenha(cone.rs.getString("senha"));
            
            Funcionario funcionario = this.funcionarioRepository.buscar(cone.rs.getInt("funcionario"));
            login.setFuncionario(funcionario);
            
        } catch(SQLException exception) {
            //JOptionPane.showMessageDialog(null, "Login não encontrado!");
            return false;
        }
        
        cone.desconectarConexao();
        return true;
    }
}
