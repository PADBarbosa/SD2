/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Barbosa
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
