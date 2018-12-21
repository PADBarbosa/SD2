package projeto;

import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
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
