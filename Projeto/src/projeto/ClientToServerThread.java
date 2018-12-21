package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class ClientToServerThread implements Runnable{
    Socket cs;
    PrintWriter out;
    BufferedReader in;

    public ClientToServerThread(Socket cs) throws IOException {
        this.cs = cs;
        //verificar isto
        PrintWriter out = new PrintWriter(cs.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
    }
    
    

    @Override
    public void run() {
        
    }
    
}
