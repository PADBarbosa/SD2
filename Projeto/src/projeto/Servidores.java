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
        try {
            Servidor s;     
            while (this.vazios.isEmpty() && this.leilao.isEmpty()) {
                c.await();
            }
            
            if (!this.vazios.isEmpty()) {
                this.vazios.get(0);
            }

            
            s.reserva(nome);
            this.ocupados.put(nome,s);
            this.vazios.remove(0);
            return 1;
        }
        catch (InterruptedException ex) {
            System.out.printf("Erro");
        }        finally {
            l.unlock();
        }
    }
}