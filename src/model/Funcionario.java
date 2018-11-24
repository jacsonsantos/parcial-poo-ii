/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jacson
 */
public class Funcionario {
    
    private int id;
    
    // 1 - Normal 2 - Gerente 3 - Administrator 
    private int  perfil;
    
    private String nome;
    
    private String rg;
    
    private String orgao_emissor;
    
    private String cpf;
    
    private String pesquisa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissor() {
        return orgao_emissor;
    }

    public void setOrgaoEmissor(String orgao_emissor) {
        this.orgao_emissor = orgao_emissor;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String getPesquisa() {
        return pesquisa;
    }
}
