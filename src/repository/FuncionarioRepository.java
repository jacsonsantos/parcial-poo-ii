/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import database.ConexaoDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Funcionario;

/**
 *
 * @author jacson
 */
public class FuncionarioRepository {
    ConexaoDB cone = new ConexaoDB();
    
    //Funcionario funcionario = new Funcionario();
    
    public void salvar(Funcionario funcionario) {
        cone.conectar();
       
        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "funcionario (nome, rg, orgao_emissor, cpf, perfil) VALUES(?,?,?,?,?)");
            
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getRg());
            ps.setString(3, funcionario.getOrgaoEmissor());
            ps.setString(4, funcionario.getCpf());
            ps.setInt(5, funcionario.getPerfil());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Funcionario Salvo!");
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro ao salvar Funcionario!");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        cone.desconectarConexao();
    }
    
    public void editar(Funcionario funcionario) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE funcionario "
                    + "SET nome = ?, rg = ?, orgao_emissor = ?, cpf = ?, perfil = ? WHERE id = ?");
            
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getRg());
            ps.setString(3, funcionario.getOrgaoEmissor());
            ps.setString(4, funcionario.getCpf());
            ps.setInt(5, funcionario.getPerfil());
            ps.setInt(6, funcionario.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Funcionario Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Funcionario!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Funcionario funcionario) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            ps.setInt(1, funcionario.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Funcionario Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Funcionario!");
        }
        
        cone.desconectarConexao();
    }
    
    public Funcionario buscar(Funcionario funcionario) {
        cone.conectar();
        cone.executeSql("SELECT * FROM funcionario WHERE cpf = "+ funcionario.getCpf());
        try{
            cone.rs.first();
            funcionario.setNome(cone.rs.getString("nome") );
            funcionario.setRg(cone.rs.getString("rg") );
            funcionario.setOrgaoEmissor(cone.rs.getString("orgao_emissor") );
            funcionario.setCpf(cone.rs.getString("cpf") );
            funcionario.setPerfil(cone.rs.getInt("perfil"));
            funcionario.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!");
        }
        
        cone.desconectarConexao();
        return funcionario;
    }
    
    public Funcionario buscar(int id) {
        cone.conectar();
        Funcionario funcionario = new Funcionario();
        cone.executeSql("SELECT * FROM funcionario WHERE id = "+ id);
        try{
            cone.rs.first();
            funcionario.setNome(cone.rs.getString("nome") );
            funcionario.setRg(cone.rs.getString("rg") );
            funcionario.setOrgaoEmissor(cone.rs.getString("orgao_emissor") );
            funcionario.setCpf(cone.rs.getString("cpf") );
            funcionario.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Funcionario não encontrado!");
        }
        
        cone.desconectarConexao();
        return funcionario;
    }
}
