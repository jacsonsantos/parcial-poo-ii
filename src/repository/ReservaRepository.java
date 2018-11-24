/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import database.ConexaoDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Hospede;
import model.Quarto;
import model.Reserva;

/**
 *
 * @author jacson
 */
public class ReservaRepository {
    ConexaoDB cone = new ConexaoDB();
    public HospedeRepository hospedeRepository;
    public QuartoRepository quartoRepository;
    //Reserva reserva = new Reserva();

    public ReservaRepository(HospedeRepository hospedeRepository,QuartoRepository quartoRepository) {
        this.hospedeRepository = hospedeRepository;
        this.quartoRepository = quartoRepository;
    }

    public ReservaRepository() {

    }
    
    public void salvar(Reserva reserva) {
        cone.conectar();

        try {
            PreparedStatement ps = cone.con.prepareStatement("INSERT INTO "
                    + "reserva (hospede, quarto, checkin, reservado, checkout, "
                    + "diaria, valor) VALUES(?,?,?,?,?,?,?)");
            
            ps.setLong(1, reserva.getHospede().getId());
            ps.setLong(2, reserva.getQuarto().getId());
            ps.setDate(3, (Date) reserva.getCheckin());
            ps.setDate(4, (Date) reserva.getReservado());
            ps.setDate(5, (Date) reserva.getCheckout());
            ps.setInt(6, reserva.getDiaria());
            ps.setFloat(7, reserva.getValor());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Reserva Salvo!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar Reserva!");
        }

        cone.desconectarConexao();
    }
    
    public void editar(Reserva reserva) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("UPDATE reserva "
                    + "SET hospede = ?, quarto = ?, checkin = ?, reservado = ?, checkout = ?, "
                    + "diaria = ?, valor = ? WHERE id = ?");
            
            ps.setInt(1, reserva.getHospede().getId());
            ps.setInt(2, reserva.getQuarto().getId());
            ps.setDate(3, (Date) reserva.getCheckin());
            ps.setDate(4, (Date) reserva.getReservado());
            ps.setDate(5, (Date) reserva.getCheckout());
            ps.setInt(6, reserva.getDiaria());
            ps.setFloat(7, reserva.getValor());
            ps.setLong(8, reserva.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Reserva Atualizado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Reserva!");
        }
        
        cone.desconectarConexao();
    }
    
    public void deletar(Reserva reserva) {
        cone.conectar();
        
        try {
            PreparedStatement ps = cone.con.prepareStatement("DELETE FROM reserva WHERE id = ?");
            ps.setLong(1, reserva.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Reserva Deletado!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar Reserva!");
        }
        
        cone.desconectarConexao();
    }
    
    public Reserva buscar(int id) {
        cone.conectar();
        Reserva reserva = new Reserva();
        cone.executeSql("SELECT * FROM reserva WHERE id = "+ id);
        try{
            cone.rs.first();
            
            Hospede hospede = this.hospedeRepository.buscar(cone.rs.getInt("hospede"));
            reserva.setHospede(hospede);
            
            Quarto quarto = this.quartoRepository.buscar(cone.rs.getInt("quarto"));
            reserva.setQuarto(quarto);
            
            reserva.setCheckin(cone.rs.getDate("checkin"));
            reserva.setReservado(cone.rs.getDate("reservado"));
            reserva.setCheckout(cone.rs.getDate("checkout"));
            reserva.setDiaria(cone.rs.getInt("diaria"));
            reserva.setValor(cone.rs.getFloat("valor"));
            reserva.setId(cone.rs.getLong("id"));
            
        } catch(SQLException exception) {
            JOptionPane.showMessageDialog(null, "Reserva não encontrado!");
        }
        
        cone.desconectarConexao();
        return reserva;
    }
}
