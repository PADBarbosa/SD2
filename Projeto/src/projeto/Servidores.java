package projeto;

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
 * Classe que contém toda a informação referente aos servidores.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */

public class Servidores {
    private final Lock l = new ReentrantLock();
    Condition c = l.newCondition();
    
    // Para a thread que vai esperar pela perda do leilão. 
    Condition cl = l.newCondition();
    /** O map de servidores usa como chave o ID do servidor e nos valores guarda os servidores. */
    private Map<Integer,Servidor> servidores;
    /** IDs dos servidores obtidos por leilão. */
    private List<Integer> leilao;
    /** IDs dos servidores vazios. */
    private List<Integer> vazios;
    /** Nome dos clientes à espera de leilão. */
    private ArrayList<Licitacao> licitacoes;
    /** Contador. */
    private int nLicitacao;

    public Servidores() {
        this.servidores = new HashMap<>();
        this.leilao = new ArrayList<>();
        this.vazios = new ArrayList<>();
        this.licitacoes = new ArrayList<>();
        this.nLicitacao = 0;
    }
    
    /**
     * Função que reserva um servidor por pedido. 
     * @param email
     * @return 
     */
    public int reservaPedido(String email) {
        l.lock();
        Servidor s;
        try {
            // A thread adormece caso não haja servidores desocupados ou ocupados por um leilão.
            while (this.vazios.isEmpty() && this.leilao.isEmpty()) {
                c.await();
            }
            // Caso existam servidores desocupados estes vão ser atribuidos automaticamente ao cliente.
            if (!this.vazios.isEmpty()) {
                s = this.servidores.get(this.vazios.get(0));
                this.vazios.remove(0);
            }
            // Caso contrário, ou seja existem servidores atribuidos num leilão, o servidor vai ser removido
            // da lista dos leilões e do cliente que o tinha até ao momento, e vai ser atribuido ao novo cliente.
            else {
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
    
    /**
     * Função que reserva um servidor por leilão.
     * @param email
     * @param valor
     * @return 
     */
    public int reservaLeilao(String email, float valor){
        l.lock();
        Servidor s;
        int i = this.nLicitacao;
        this.nLicitacao++;
        try{
            Licitacao li = new Licitacao(valor, i);
            this.licitacoes.add(li);
            // A thread adormece caso não haja servidores livres ou não seja a melhor licitação
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
    
    /**
     * Função que adiciona um servidor.
     * @param id
     * @param tipo
     * @param custoHorario 
     */
    public synchronized void adicionaServidores(int id, String tipo, Float custoHorario){
        Servidor s = new Servidor(id, tipo, custoHorario);
        this.servidores.put(id, s);
        this.vazios.add(id);
    }
    
    /**
     * Função que liberta um servidor.
     * @param id 
     */
    public void libertaServidor(int id) {
        l.lock();
        Servidor s = this.servidores.get(id);
        s.liberta();
        // Remove o servidor da lista de leilões, caso este tenha sido reservado por um.
        if(this.leilao.contains(id)){
            this.leilao.remove(Integer.valueOf(id));
            cl.signalAll();
        }
        this.vazios.add(id);
        this.licitacoes.sort(new LicitacaoComparator());
        this.c.signalAll();
        l.unlock();   
    }  
    
    /**
     * Função que aguarda que a thread seja cancelada.
     * @param id
     * @param email 
     */
    public void esperaPerderLeilao(int id, String email) {
        this.l.lock();
        
        try {
            while(this.leilao.contains(id) && this.servidores.get(id).getCliente().equals(email)) {
                cl.await();
            }
        }catch (InterruptedException ex) {
            Logger.getLogger(Servidores.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            this.l.unlock();
        }   
    }
}


