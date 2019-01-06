package projeto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * Classe que contém informação referente aos servidores.
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Registo {
    private int contador;
    private int numeroReserva;
    private Map<String, Servidores> servidores;

    public Registo() {
        this.contador = 1;
        this.numeroReserva = 1;
        this.servidores = new HashMap<>();
        for( String s : Tipos.getTipos()) {
            this.servidores.put(s, new Servidores());
        }
        adicionaServidor("large");
        adicionaServidor("medium");
        adicionaServidor("medium");
        adicionaServidor("small");
        adicionaServidor("small");
        adicionaServidor("small");
    }
    
    /**
     * Função que adiciona um servidor.
     * @param tipo 
     */
    public void adicionaServidor(String tipo) {
        Servidores s = this.servidores.get(tipo);
        s.adicionaServidores(this.contador, tipo, Tipos.getTaxa(tipo));
        this.contador++;
    }
    
    /**
     * Função que reserva um servidor, a pedido, tendo em conta o seu tipo.
     * @param tipo
     * @param email
     * @return Reserva
     */
    public Reserva reservaPedido(String tipo, String email){       
        Servidores s = this.servidores.get(tipo);
        int id = s.reservaPedido(email);
        LocalDateTime data = LocalDateTime.now();      
        Reserva r = new Reserva(numeroReserva , id, Tipos.getTaxa(tipo), data, tipo);
        incrementaNumeroReserva();
        return r;
    }
    
    /**
     * Função que reserva um servidor, por leilão, tendo em conta o seu tipo.
     * @param tipo
     * @param email
     * @param valor
     * @return Reserva
     */
    public Reserva reservaLeilao(String tipo, String email, float valor){
        Servidores s = this.servidores.get(tipo);
        int id = s.reservaLeilao(email, valor);
        LocalDateTime data = LocalDateTime.now();
        Reserva r = new Reserva(numeroReserva, id, valor, data, tipo);
        incrementaNumeroReserva();
        return r;
    }
    
    /**
     * Função que liberta um servidor.
     * @param tipo
     * @param id 
     */
    public void retiraServidor(String tipo, int id){      
        Servidores s = this.servidores.get(tipo);
        s.libertaServidor(id);
    }
    
    /**
     * Funçção que força a thread a esperar até ao momento em que a reserva é cancelada.
     * @param tipo
     * @param id
     * @param email 
     */
    public void esperaPerderLeilao(String tipo, int id, String email) {
        Servidores s = this.servidores.get(tipo);
        System.out.println("entrou no registo");
        s.esperaPerderLeilao(id,email);
    }
    
    /**
     * Incrementa o número de reserva.
     */
    public synchronized void incrementaNumeroReserva(){
        this.numeroReserva++;
    }
}