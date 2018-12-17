/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ze
 */
public class Registo {
    private Map<String,Servidor> large;
    private List<Servidor> largeVazios;
    
    private Map<String,Servidor> medium;
    private List<Servidor> mediumVazios;

    private Map<String,Servidor> small;
    private List<Servidor> smallVazios;


    
    public int reservaPedidoLarge(String nome) {
        Servidor s;        
        s = this.largeVazios.get(0);
        if (s == null) return -1;
        s.reserva(nome);
        this.large.put(nome,s);
        this.largeVazios.remove(0);
        return 1;
    }
 
}