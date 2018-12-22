package projeto;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Registo {
    int contador;
    private Map<String, Servidores> servidores;
    private Servidores large;
    private Servidores medium;
    private Servidores small;

    public Registo() {
        this.contador = 1;
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
    
    public void adicionaServidor(String tipo) {
        Servidores s = this.servidores.get(tipo);
        s.adicionaServidores(this.contador, tipo, Tipos.getTaxa(tipo));
        this.contador++;
    }
    
    public int reservaPedido(String tipo, String email){
        Servidores s = this.servidores.get(tipo);
        return s.reservaPedido(email);
    }
    
    public int reservaLeilao(String tipo, String email, float valor){
        Servidores s = this.servidores.get(tipo);
        return s.reservaLeilao(email, valor);
    }
    
    public void retiraServidor(String tipo, int id){      
        Servidores s = this.servidores.get(tipo);
        s.libertaServidor(id);
    }
    
    public void esperaPerderLeilao(String tipo, int id, String email) {
        Servidores s = this.servidores.get(tipo);
        System.out.println("entrou no registo");
        s.esperaPerderLeilao(id,email);
    }
    
    
}