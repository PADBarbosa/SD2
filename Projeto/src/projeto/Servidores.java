/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
    
    //chave-> id do servidor
    //guarda todos os servidores
    private Map<Integer,Servidor> servidores;

    //ids dos servidores obtidos por leilao
    private List<Integer> leilao;
    
    //ids dos servidores vazios
    private List<Integer> vazios;
    
    //nome dos clientes à espera de leilão
    private TreeSet<Licitacao> licitacoes;
    
    private int melhorLicitacao;
    
    //contador 
    private int nLicitacao;

    public Servidores() {
        this.servidores = new HashMap<>();
        this.leilao = new ArrayList<>();
        this.vazios = new ArrayList<>();
        this.licitacoes = new TreeSet<>(new LicitacaoComparator());
        this.nLicitacao = 0;
    }
    
    public int reservaPedido(String email) {
        l.lock();
        Servidor s;
        try {
            while (this.vazios.isEmpty() && this.leilao.isEmpty()) {
                c.await();
            }           
            if (!this.vazios.isEmpty()) {
                s = this.servidores.get(this.vazios.get(0));
                this.vazios.remove(0);
            }
            else { //Mudar para valor mais baixo
                s = this.servidores.get(this.leilao.get(0));
                this.leilao.remove(0);
            }
            s.reserva(email);         
            return s.getId();          
        }
        catch (InterruptedException ex) {
            System.out.printf("Erro");
            return -1;
        }        
        finally {
            l.unlock();
        }    
    }
    
    public int reservaLeilao(String email, float valor){
        l.lock();
        Servidor s;
        int i = this.nLicitacao;
        try{
            Licitacao l = new Licitacao(valor, i);
            this.licitacoes.add(l);
            this.melhorLicitacao = this.licitacoes.last().getId();
            while(this.vazios.isEmpty() || (i != this.melhorLicitacao)){
                c.await();
            }
            s = this.servidores.get(this.vazios.get(0));
            this.vazios.remove(0);
            s.reserva(email);
            this.leilao.add(s.getId());
           this.licitacoes.remove(l);
            return s.getId();
        }
        catch(InterruptedException ex){
            System.out.printf("Erro");
            return -1;
        }
        finally {
            l.unlock();
        }  
    }
    
    public void adicionaServidores(int id, String tipo, Float custoHorario){
        Servidor s = new Servidor(id, tipo, custoHorario);
        this.servidores.put(id, s);
        this.vazios.add(id);
    }
    
    public void libertaServidor(int id) {
        l.lock();
        Servidor s = this.servidores.get(id);
        s.liberta();
        if(this.leilao.contains(id)){
            this.leilao.remove(Integer.valueOf(id));
        }
        this.vazios.add(id);
        this.c.signalAll();
        l.unlock();   
    }    
}