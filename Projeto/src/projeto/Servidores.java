/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.PrintWriter;
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
    
    //para a thread que vai esperar pela perda do leilao 
   // private Lock ll = new ReentrantLock();
    Condition cl = l.newCondition();
    
    //chave-> id do servidor
    //guarda todos os servidores
    private Map<Integer,Servidor> servidores;

    //ids dos servidores obtidos por leilao
    private List<Integer> leilao;
    
    //ids dos servidores vazios
    private List<Integer> vazios;
    
    //nome dos clientes à espera de leilão
    private ArrayList<Licitacao> licitacoes;
    
    private int melhorLicitacao;
    
    //contador 
    private int nLicitacao;

    public Servidores() {
        this.servidores = new HashMap<>();
        this.leilao = new ArrayList<>();
        this.vazios = new ArrayList<>();
        this.licitacoes = new ArrayList<>();
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
                cl.signalAll();
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
        int i = this.nLicitacao; // é preciso?
        try{
            Licitacao l = new Licitacao(valor, i);
            this.licitacoes.add(l);
            while(this.vazios.isEmpty() || (i != this.licitacoes.get(0).getId())){
                c.await();
            }
            s = this.servidores.get(this.vazios.get(0));
            this.vazios.remove(0);
            s.reserva(email);
            this.leilao.add(s.getId());
            this.licitacoes.remove(0);
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
            cl.signalAll();
        }
        this.vazios.add(id);
        this.licitacoes.sort(new LicitacaoComparator());
        this.c.signalAll();
        l.unlock();   
    }  
    
    //1 se foi libertado
    //0 se foi forçado
    public void esperaPerderLeilao(int id, String email) {
        System.out.println("começou");
        this.l.lock();
        
        try {
            while(this.leilao.contains(Integer.valueOf(id)) && this.servidores.get(id).getCliente().equals(email)) {
                System.out.println("adormeceu");
                cl.await();
                System.out.println("acordou");
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(Servidores.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {
            System.out.println("saiu");
            this.l.unlock();
        }
        
    }
}


