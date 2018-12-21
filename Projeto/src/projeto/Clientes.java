package projeto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Clientes {
    private Map<String, Cliente> clientes;
    
    public Clientes() {
        this.clientes = new HashMap<>();
        Cliente c = new Cliente("pad", 0, new HashMap<>());
        this.adicionaCliente(c);
    }
    
    public synchronized void adicionaCliente(Cliente c){
        this.clientes.put(c.getEmail(), c);
    }
    
    public synchronized Cliente getPorEmail(String email){
        return clientes.get(email);
    }
}
