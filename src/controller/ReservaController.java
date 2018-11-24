/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Reserva;
import repository.ReservaRepository;

/**
 *
 * @author jacson
 */
public class ReservaController {
    
  private ReservaRepository reservaRepository;

  public ReservaController(){
      this.reservaRepository = new ReservaRepository();
  }
  
  public void salvar(Reserva reserva){
      
      this.reservaRepository.salvar(reserva);
  }
  
  public void editar(Reserva reserva){
      
      this.reservaRepository.editar(reserva);
  }
}
