/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Funcionario;
import repository.FuncionarioRepository;

/**
 *
 * @author jacson
 */
public class FuncionarioController {
    
    private FuncionarioRepository funcionarioRepository;
    
    public FuncionarioController() {
        this.funcionarioRepository = new FuncionarioRepository();
    }
    
    public void salvar(Funcionario funcionario)
    {
        this.funcionarioRepository.salvar(funcionario);
    }
}
