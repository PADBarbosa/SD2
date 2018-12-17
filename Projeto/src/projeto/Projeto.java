/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author Barbosa
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
