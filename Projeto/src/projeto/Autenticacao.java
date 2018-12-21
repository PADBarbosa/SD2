package projeto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Autenticacao {
    private Map<String, String> users;
    
    public Autenticacao() {
        this.users = new HashMap<>();
        users.put("pad", "123");
    }
    
    public Autenticacao(Map<String, String> users) {
        this.users = users;
    }
    
    public synchronized boolean registaUser(String email, String password){
        if(users.containsKey(email)){
            return false;
        }
        this.users.put(email, password);
        return true;
    }
    
    public synchronized boolean verificaUser(String email, String password){
        if(this.users.containsKey(email)){
            if(this.users.get(email).equals(password)){
                return true;
            }
        }
        return false;
        
    }
}
