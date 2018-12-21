package projeto;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Projeto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Autenticacao aut = new Autenticacao();
        
        String email1 = "padbarbosa@gmail.com";
        String password1 = "123";
        
        String email2 = "padbarbosa@gmail.com";
        String password2 = "123";
        
        aut.registaUser(email1, password1);
        aut.verificaUser(email2, password2);
        
    }
    
}
