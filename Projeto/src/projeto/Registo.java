/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ze
 */
public class Registo {
    private Servidores large;
    private Servidores medium;
    private Servidores small;

    public Registo() {
        this.large = new Servidores();
        this.large.adicionaServidores(1, "large", (float) 0.5);
        this.large.adicionaServidores(2, "large", (float) 0.5);
        this.large.adicionaServidores(3, "large", (float) 0.5);
        
        this.medium = new Servidores();
        this.small = new Servidores();
    }
    
    public int reservaPedido(String tipo, String email){
        if(tipo.equals("large")){
            return this.large.reservaPedido(email);
        }
        else if(tipo.equals("medium")){
            return this.medium.reservaPedido(email);
        }
        else if(tipo.equals("small")){
            return this.small.reservaPedido(email);
        }
        return -1;
    }
    
    
 
}