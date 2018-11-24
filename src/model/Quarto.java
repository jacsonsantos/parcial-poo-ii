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
public class Quarto {
    
    private int id;
    
    private int numero;
    
    //
    private int status;
    
    private String nome;
    
    //1- basico 2- luxo
    private int categoria;
    
    private String pesquisa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    public void setPesquis(String pesquisa) {
        this.pesquisa = pesquisa;
    }
    
    public String getPesquisa() {
        return pesquisa;
    }
}