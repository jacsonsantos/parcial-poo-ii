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
import model.Hospede;

/**
 *
 * @author jacson
 */
public class HospedeRepository {
    ConexaoDB cone = new ConexaoDB();
    
    //Hospede hospede = new Hospede();
    
    public void salvar(Hospede hospede) {
        cone.conectar();
       
        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "hospedes (pais, cep, estado, cidade, bairro, endereco, "
                    + "numero, complemento, rg, orgao_emissor, cpf, "
                    + "nacionalidade, naturalidade, celular, telefone, email, "
                    + "nome, profissao) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?)");
            
            ps.setString(1, hospede.getPais());
            ps.setString(2, hospede.getCep());
            ps.setString(3, hospede.getEstado());
            ps.setString(4, hospede.getCidade());
            ps.setString(5, hospede.getBairro());
            ps.setString(6, hospede.getEndereco());
            ps.setInt(7, hospede.getNumero());
            ps.setString(8, hospede.getComplemento());
            ps.setString(9, hospede.getRg());
            ps.setString(10, hospede.getOrgaoEmissor());
            ps.setString(11, hospede.getCpf());
            ps.setString(12, hospede.getNacionalidade());
            ps.setString(13, hospede.getNaturalidade());
            ps.setString(14, hospede.getCelular());
            ps.setString(15, hospede.getTelefone());
            ps.setString(16, hospede.getEmail());
            ps.setString(17, hospede.getNome());
            ps.setString(18, hospede.getProfissao());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Hospede Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Hospede!");
        }
        
        cone.desconectarConexao();
    }
    
    public void editar(Hospede hospede) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE hospedes "
                    + "SET pais = ?, cep = ?, estado = ?, cidade = ?, bairro = ?"
                    + ", endereco = ?, numero = ?, complemento = ?, rg = ?, "
                    + "orgao_emissor = ?, cpf = ?, nacionalidade = ?, "
                    + "naturalidade = ?, celular = ?, telefone = ?, email = ?, "
                    + "nome = ?, profissao = ? WHERE id = ?");
            
            ps.setString(1, hospede.getPais());
            ps.setString(2, hospede.getCep());
            ps.setString(3, hospede.getEstado());
            ps.setString(4, hospede.getCidade());
            ps.setString(5, hospede.getBairro());
            ps.setString(6, hospede.getEndereco());
            ps.setInt(7, hospede.getNumero());
            ps.setString(8, hospede.getComplemento());
            ps.setString(9, hospede.getRg());
            ps.setString(10, hospede.getOrgaoEmissor());
            ps.setString(11, hospede.getCpf());
            ps.setString(12, hospede.getNacionalidade());
            ps.setString(13, hospede.getNaturalidade());
            ps.setString(14, hospede.getCelular());
            ps.setString(15, hospede.getTelefone());
            ps.setString(16, hospede.getEmail());
            ps.setString(17, hospede.getNome());
            ps.setString(18, hospede.getProfissao());
            ps.setInt(19, hospede.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Hospede Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Hospede!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Hospede hospede) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM hospedes WHERE id = ?");
            ps.setInt(1, hospede.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Hospede Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Hospede!");
        }
        
        cone.desconectarConexao();
    }
    
    public Hospede buscar(Hospede hospede) {
        cone.conectar();
        cone.executeSql("SELECT * FROM hospedes WHERE nome like '%"+ hospede.getPesquisa() +"%'");
        try{
            cone.rs.first();
            hospede.setPais( cone.rs.getString("pais") );
            hospede.setCep( cone.rs.getString("cep") );
            hospede.setEstado( cone.rs.getString("estado") );
            hospede.setCidade( cone.rs.getString("cidade") );
            hospede.setBairro( cone.rs.getString("bairro") );
            hospede.setEndereco( cone.rs.getString("endereco") );
            hospede.setNumero( Integer.valueOf(cone.rs.getInt("numero")) );
            hospede.setComplemento( cone.rs.getString("complemento") );
            hospede.setRg( cone.rs.getString("rg") );
            hospede.setOrgaoEmissor( cone.rs.getString("orgao_emissor") );
            hospede.setCpf( cone.rs.getString("cpf") );
            hospede.setNacionalidade( cone.rs.getString("nacionalidade") );
            hospede.setNaturalidade( cone.rs.getString("naturalidade") );
            hospede.setCelular( cone.rs.getString("celular") );
            hospede.setTelefone( cone.rs.getString("telefone") );
            hospede.setEmail( cone.rs.getString("email") );
            hospede.setNome( cone.rs.getString("nome") );
            hospede.setProfissao( cone.rs.getString("profissao") );
            hospede.setId(Long.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Hospede não encontrado!");
        }
        
        cone.desconectarConexao();
        return hospede;
    }
    
    public Hospede buscar(int id) {
        cone.conectar();
        Hospede hospede = new Hospede();
        cone.executeSql("SELECT * FROM hospedes WHERE id = "+ id);
        try{
            cone.rs.first();
            hospede.setPais( cone.rs.getString("pais") );
            hospede.setCep( cone.rs.getString("cep") );
            hospede.setEstado( cone.rs.getString("estado") );
            hospede.setCidade( cone.rs.getString("cidade") );
            hospede.setBairro( cone.rs.getString("bairro") );
            hospede.setEndereco( cone.rs.getString("endereco") );
            hospede.setNumero( Integer.valueOf(cone.rs.getInt("numero")) );
            hospede.setComplemento( cone.rs.getString("complemento") );
            hospede.setRg( cone.rs.getString("rg") );
            hospede.setOrgaoEmissor( cone.rs.getString("orgao_emissor") );
            hospede.setCpf( cone.rs.getString("cpf") );
            hospede.setNacionalidade( cone.rs.getString("nacionalidade") );
            hospede.setNaturalidade( cone.rs.getString("naturalidade") );
            hospede.setCelular( cone.rs.getString("celular") );
            hospede.setTelefone( cone.rs.getString("telefone") );
            hospede.setEmail( cone.rs.getString("email") );
            hospede.setNome( cone.rs.getString("nome") );
            hospede.setProfissao( cone.rs.getString("profissao") );
            hospede.setId(Long.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Hospede não encontrado!");
        }
        
        cone.desconectarConexao();
        return hospede;
    }
}
