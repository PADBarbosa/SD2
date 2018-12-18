/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ze
 */
public class Servidores {
    private Lock l = new ReentrantLock();
    Condition c = l.newCondition();
 
    
    
    private Map<String,Servidor> pedido;
    private List<Servidor> leilao;
    private List<Servidor> vazios;

    public Servidores() {
        this.pedido = new HashMap<>();
        this.leilao = new ArrayList<>();
        this.vazios = new ArrayList<>();
    }
    
    


    
    public int reservaPedido(String nome) {
        
        l.lock();
        Servidor s;
        try {
                 
            while (this.vazios.isEmpty() && this.leilao.isEmpty()) {
                c.await();
            }
            
            if (!this.vazios.isEmpty()) {
                s = this.vazios.get(0);
                this.vazios.remove(0);

            }
            else { //Mudar para valor mais baixo
                s = this.leilao.get(0);
                this.leilao.remove(0);
            }
            
            s.reserva(nome);
            this.pedido.put(nome,s);
           

            
        }
        catch (InterruptedException ex) {
            System.out.printf("Erro"); 
            return -1;
        }        
        finally {
            l.unlock();
            return s.getId();  
            
        }
         
    }
}