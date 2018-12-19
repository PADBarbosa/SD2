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
                    
                    //criar conta ou autenticar?
                    //0 criar
                    //1 autenticar
                    
                    
                    
                    
                    boolean autenticado = false;
                    
                    boolean sucesso;
                    while(!autenticado) {
                        String x = in.readLine();
                        String email = in.readLine();
                        String password = in.readLine();
                        System.out.println(x);
                        System.out.println(email);
                        System.out.println(password);
                        if(x.equals("0")) {
                            sucesso = this.autenticacao.registaUser(email, password);
                            if (sucesso) {
                                out.println("Conta criada com sucesso");
                              //  System.out.println("Conta criada com sucessoawfgsdgfh");
                            }
                            else {
                                out.println("Email já registado");
                            }

                        }
                        else if(x.equals("1")) {
                            sucesso = this.autenticacao.verificaUser(email, password);
                            if (sucesso) {
                                out.println("Autenticado");
                                autenticado = true;
                            }
                            else {
                                out.println("email ou password errados");
                            }
                        }
                    }
                    
                    
                    
                        
                        
                        
                        
                        
                        
                     /*   
                        
                        
                        
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
			cs.close();*/
		} catch (Exception e){System.out.println("Asneira");}
	}
}
