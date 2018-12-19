/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ze
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
