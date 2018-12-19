/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author ze
 */
public class Server {
    
    
    
    public static void main(String args[]) throws IOException{
                Autenticacao autenticacao;
		ServerSocket ss = new ServerSocket(9999);
                autenticacao = new Autenticacao();
                Registo registo = new Registo();
                Clientes clientes = new Clientes();
		while(true) {
                    (new Thread (new ServerRunnable(ss.accept(), autenticacao, registo, clientes))).start();
                    
                   
                    
                    
                
			//(new Thread (new ServerRunnable())).start();
		}
		//ss.close();
	}
}
