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
import model.Quarto;

/**
 *
 * @author jacson
 */
public class QuartoRepository {
    ConexaoDB cone = new ConexaoDB();
    
    //Quarto quarto = new Quarto();
    
    public void salvar(Quarto quarto) {
        cone.conectar();
       
        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "quarto (nome, numero, categoria, status) VALUES(?,?,?,?)");
            
            ps.setString(1, quarto.getNome());
            ps.setInt(2, quarto.getNumero());
            ps.setInt(3, quarto.getCategoria());
            ps.setInt(4, quarto.getStatus());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Quarto Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Quarto!");
        }
        
        cone.desconectarConexao();
    }
    
    public void editar(Quarto quarto) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE quarto "
                    + "SET nome = ?, numero = ?, categoria = ?, status = ? WHERE id = ?");
            
            ps.setString(1, quarto.getNome());
            ps.setInt(2, quarto.getNumero());
            ps.setInt(3, quarto.getCategoria());
            ps.setInt(4, quarto.getStatus());
            ps.setInt(5, quarto.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Quarto Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Quarto!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Quarto quarto) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM quarto WHERE id = ?");
            ps.setInt(1, quarto.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Quarto Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Quarto!");
        }
        
        cone.desconectarConexao();
    }
    
    public Quarto buscar(Quarto quarto) {
        cone.conectar();
        cone.executeSql("SELECT * FROM quarto WHERE nome like '%"+ quarto.getPesquisa() +"%'");
        try{
            cone.rs.first();
            quarto.setNome( cone.rs.getString("nome") );
            quarto.setNumero(Integer.valueOf(cone.rs.getInt("numero")) );
            quarto.setCategoria(Integer.valueOf(cone.rs.getInt("categoria")) );
            quarto.setStatus(Integer.valueOf(cone.rs.getInt("status")) );
            quarto.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Quarto não encontrado!");
        }
        
        cone.desconectarConexao();
        return quarto;
    }
    
    public Quarto buscar(int id) {
        cone.conectar();
        Quarto quarto = new Quarto();
        cone.executeSql("SELECT * FROM quarto WHERE id = "+ id);
        try{
            cone.rs.first();
            quarto.setNome( cone.rs.getString("nome") );
            quarto.setNumero(Integer.valueOf(cone.rs.getInt("numero")) );
            quarto.setCategoria(Integer.valueOf(cone.rs.getInt("categoria")) );
            quarto.setStatus(Integer.valueOf(cone.rs.getInt("status")) );
            quarto.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Quarto não encontrado!");
        }
        
        cone.desconectarConexao();
        return quarto;
    }
}
