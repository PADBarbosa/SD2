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
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ze
 */
public class Server {
    
    
    
    public static void main(String args[]) throws IOException{
                Autenticacao autenticacao;
		ServerSocket ss = new ServerSocket(9999);
                autenticacao = new Autenticacao();
		while(true) {
                    (new Thread (new ServerRunnable(ss.accept(), autenticacao))).start();
                    
                   
                    
                    
                
			//(new Thread (new ServerRunnable())).start();
		}
		//ss.close();
	}
}
