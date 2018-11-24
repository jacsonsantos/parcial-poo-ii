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
import model.Estabelecimento;

/**
 *
 * @author jacson
 */
public class EstabelecimentoRepository {
    ConexaoDB cone = new ConexaoDB();
    
    //Estabelecimento estabelecimento = new Estabelecimento();
    
    public void salvar(Estabelecimento estabelecimento) {
        cone.conectar();
       
        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "estabelecimento (nome, endereco, telefone, cnpj, logomarca, cidade,"
                    + " cep, numero, complemento, estado, email) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setString(1, estabelecimento.getNome());
            ps.setString(2, estabelecimento.getEndereco());
            ps.setString(3, estabelecimento.getTelefone());
            ps.setString(4, estabelecimento.getCnpj());
            ps.setString(5, estabelecimento.getLogomarca());
            ps.setString(6, estabelecimento.getCidade());
            ps.setString(7, estabelecimento.getCep());
            ps.setInt(8, estabelecimento.getNumero());
            ps.setString(9, estabelecimento.getComplemento());
            ps.setString(10, estabelecimento.getEstado());
            ps.setString(11, estabelecimento.getEmail());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Estabelecimento Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Estabelecimento!");
        }
        
        cone.desconectarConexao();
    }
    
    public void editar(Estabelecimento estabelecimento) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE estabelecimento "
                    + "SET nome = ?, endereco = ?, telefone = ?, cnpj = ?, logomarca = ?, cidade = ?,"
                    + " cep = ?, numero = ?, complemento = ?, estado = ?, email = ? WHERE id = ?");
            
            ps.setString(1, estabelecimento.getNome());
            ps.setString(2, estabelecimento.getEndereco());
            ps.setString(3, estabelecimento.getTelefone());
            ps.setString(4, estabelecimento.getCnpj());
            ps.setString(5, estabelecimento.getLogomarca());
            ps.setString(6, estabelecimento.getCidade());
            ps.setString(7, estabelecimento.getCep());
            ps.setInt(8, estabelecimento.getNumero());
            ps.setString(9, estabelecimento.getComplemento());
            ps.setString(10, estabelecimento.getEstado());
            ps.setString(11, estabelecimento.getEmail());
            ps.setInt(12, estabelecimento.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Estabelecimento Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Estabelecimento!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Estabelecimento estabelecimento) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM estabelecimento WHERE id = ?");
            ps.setInt(1, estabelecimento.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Estabelecimento Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Estabelecimento!");
        }
        
        cone.desconectarConexao();
    }
    
    public Estabelecimento buscar(int id) {
        cone.conectar();
        Estabelecimento estabelecimento = new Estabelecimento();
        cone.executeSql("SELECT * FROM estabelecimentos WHERE id = "+ id);
        try{
            cone.rs.first();
            estabelecimento.setNome( cone.rs.getString("nome") );
            estabelecimento.setEndereco( cone.rs.getString("endereco") );
            estabelecimento.setTelefone( cone.rs.getString("telefone") );
            estabelecimento.setCnpj(cone.rs.getString("cnpj") );
            estabelecimento.setLogomarca(cone.rs.getString("logomarca") );
            estabelecimento.setCidade( cone.rs.getString("cidade") );
            estabelecimento.setCep( cone.rs.getString("cep") );
            estabelecimento.setNumero( Integer.valueOf(cone.rs.getInt("numero")) );
            estabelecimento.setComplemento( cone.rs.getString("complemento") );
            estabelecimento.setEstado( cone.rs.getString("estado") );
            estabelecimento.setEmail( cone.rs.getString("email") );
            estabelecimento.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Estabelecimento não encontrado!");
        }
        
        cone.desconectarConexao();
        return estabelecimento;
    }
}
