package projeto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Classe responsável por registar, autenticar e guardar a informação de login  dos utilizadores.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Autenticacao {
    /** A key do map é referente ao email do utilizador e o value a password do mesmo */
    private Map<String, String> users;
    
    public Autenticacao() {
        this.users = new HashMap<>();
        users.put("pad", "123");
    }
    
    public Autenticacao(Map<String, String> users) {
        this.users = users;
    }
    
     /** 
     * Função que regista um novo cliente com a informaçção passada por argumento,
     * desde que não exista nenhum utilizador com o email indicado.
     * @param email
     * @param password
     * @return boolean true se conseguiu registar
     */
    public synchronized boolean registaUser(String email, String password){
        if(users.containsKey(email)){
            return false;
        }
        this.users.put(email, password);
        return true;
    }
    
    /**
     * Função que verifica se o email e password indicados são compativeis e se fazem
     * parte dos utilizadores registados.
     * @param email
     * @param password
     * @return true caso se verifique
     */
    public synchronized boolean verificaUser(String email, String password){
        if(this.users.containsKey(email)){
            if(this.users.get(email).equals(password)){
                return true;
            }
        }
        return false;
        
    }
}
