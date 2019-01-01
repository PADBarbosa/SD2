package projeto;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Classe que guarda todos os clientes do servidor.
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Clientes {
    /** O map guarda nas keys o email do cliente e o valor é o cliente correspondente a esse email */
    private Map<String, Cliente> clientes;
    
    public Clientes() {
        this.clientes = new HashMap<>();
        Cliente c = new Cliente("pad", 0, new HashMap<>());
        this.adicionaCliente(c);
    }
    
    /**
     * Função que adiciona um cliente.
     * @param c 
     */
    public synchronized void adicionaCliente(Cliente c){
        this.clientes.put(c.getEmail(), c);
    }
    
    /**
     * Funçção que vai buscar o cliente respetivo ao email passado como argumento.
     * @param email
     * @return Cliente
     */
    public synchronized Cliente getPorEmail(String email){
        return clientes.get(email);
    }
}
