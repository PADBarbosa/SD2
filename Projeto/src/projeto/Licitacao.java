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
    private int id;

    public Licitacao(float valor, int id) {
        this.valor = valor;
        this.id = id;
    }
    
    public float getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
