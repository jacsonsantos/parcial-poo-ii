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
import model.Produto;

/**
 *
 * @author jacson
 */
public class ProdutoRepository {
    ConexaoDB cone = new ConexaoDB();
    //Produto produto = new Produto();
    
    public void salvar(Produto produto) {
        cone.conectar();

        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "produto (nome, categoria, valor) VALUES(?,?,?)");
            
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getCategoria());
            ps.setFloat(3, produto.getValor());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Produto!");
        }

        cone.desconectarConexao();
    }
    
    public void editar(Produto produto) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE produto "
                    + "SET nome = ?, cagetogia = ?, valor = ? WHERE id = ?");
            
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getCategoria());
            ps.setFloat(3, produto.getValor());
            ps.setInt(4, produto.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Produto!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Produto produto) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM produto WHERE id = ?");
            ps.setInt(1, produto.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Produto Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Produto!");
        }
        
        cone.desconectarConexao();
    }
    
    public Produto buscar(int id) {
        cone.conectar();
        Produto produto = new Produto();
        cone.executeSql("SELECT * FROM produto WHERE id = "+ id);
        try{
            cone.rs.first();
            produto.setId(cone.rs.getInt("id"));
            produto.setNome(cone.rs.getString("nome"));
            produto.setCategoria(cone.rs.getInt("categoria"));
            produto.setValor(cone.rs.getFloat("valor"));
            
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!");
        }
        
        cone.desconectarConexao();
        return produto;
    }
}
