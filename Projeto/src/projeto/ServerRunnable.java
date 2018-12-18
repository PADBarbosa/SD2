/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author ze
 */
class ServerRunnable implements Runnable{
	Socket cs;
        Autenticacao autenticacao;

	ServerRunnable(Socket clientSocket, Autenticacao autenticacao){
		this.cs = clientSocket;
                this.autenticacao = autenticacao;
	}

	public void run() {
		try {
                    PrintWriter out = new PrintWriter(this.cs.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(this.cs.getInputStream()));
                    
                    out.println("Escreva o seu email");
                    String email = in.readLine();
                    out.println("Escreva a sua password");
                    String password = in.readLine();
                    
                    
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
			int total = 0;
			int n = 0;
			String current;
			int c;
			while((current = in.readLine()) != null) {
				c = Integer.parseInt(current);
				System.out.println(c);
				total += c;
				n++;
				out.println("Total " + total);
			}
			out.println("Média " + (float) total / n);

			out.close();
			cs.close();
		} catch (Exception e){System.out.println("Asneira");}
	}
}
