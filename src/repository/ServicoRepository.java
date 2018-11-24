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
import model.Reserva;
import model.Servico;

/**
 *
 * @author jacson
 */
public class ServicoRepository {
    ConexaoDB cone = new ConexaoDB();
    private ReservaRepository reservaRepository;
    private ProdutoRepository produtoRepository;
    //Servico servico = new Servico();

    public ServicoRepository() {
        this.reservaRepository = new ReservaRepository(
                                        new HospedeRepository(),
                                        new QuartoRepository()
                                     );
        this.produtoRepository = new ProdutoRepository();
    }
    
    public void salvar(Servico servico) {
        cone.conectar();
       
        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "servico (reserva, produto, quantidade) VALUES(?,?,?)");
            
            ps.setLong(1, servico.getReserva().getId());
            ps.setInt(2, servico.getProduto().getId());
            ps.setInt(3, servico.getQuantidade());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Servico Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Servico!");
        }
        
        cone.desconectarConexao();
    }
    
    public void editar(Servico servico) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE servico "
                    + "SET reserva = ?, produto = ?, quantidade = ? WHERE id = ?");
            
            ps.setLong(1, servico.getReserva().getId());
            ps.setInt(2, servico.getProduto().getId());
            ps.setInt(3, servico.getQuantidade());
            ps.setInt(4, servico.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Servico Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Servico!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Servico servico) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM servico WHERE id = ?");
            ps.setInt(1, servico.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Servico Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Servico!");
        }
        
        cone.desconectarConexao();
    }
    
    public Servico buscar(int id) {
        cone.conectar();
        Servico servico = new Servico();
        cone.executeSql("SELECT * FROM servico WHERE id = "+ id);
        try{
            cone.rs.first();
            Reserva reserva = this.reservaRepository.buscar(cone.rs.getInt("reserva"));
            servico.setReserva(reserva);
            
            Produto produto = this.produtoRepository.buscar(cone.rs.getInt("produto"));
            servico.setProduto(produto);
            
            servico.setQuantidade(cone.rs.getInt("quantidade"));
            servico.setId(cone.rs.getInt("id"));
            
            servico.setId(Integer.valueOf(cone.rs.getInt("id")));
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Servico não encontrado!");
        }
        
        cone.desconectarConexao();
        return servico;
    }
}
