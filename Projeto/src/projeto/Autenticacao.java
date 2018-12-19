package projeto;


import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Barbosa
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

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
    
    public boolean registaUser(String email, String password){
        if(users.containsKey(email)){
            return false;
        }
        this.users.put(email, password);
        return true;
    }
    
    public boolean verificaUser(String email, String password){
        if(this.users.containsKey(email)){
            if(this.users.get(email).equals(password)){
                return true;
            }
        }
        return false;
        
    }
    
}
