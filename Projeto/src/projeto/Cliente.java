/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author Barbosa
 */
public class Cliente {
    String nome;
    String email;
    String password;
    float valorDivida;

    public Cliente(String nome, String email, String password, float valorDivida) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.valorDivida = valorDivida;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getValorDivida() {
        return valorDivida;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValor(float valorDivida) {
        this.valorDivida = valorDivida;
    }
    
    
}