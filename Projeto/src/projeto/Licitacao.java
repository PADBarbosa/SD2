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
public class Licitacao {
    private float valor;
    private String cliente;

    public Licitacao(float valor, String cliente) {
        this.valor = valor;
        this.cliente = cliente;
    }
    
    public float getValor() {
        return valor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    
    
}
