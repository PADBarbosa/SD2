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
import java.net.UnknownHostException;

/**
 *
 * @author ze
 */
public class Client {
    public static void main(String args[]) throws IOException, UnknownHostException{
		Socket cs = new Socket("127.0.0.1", 9999);

		PrintWriter toServer = new PrintWriter(cs.getOutputStream(), true);
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(cs.getInputStream()));
                
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                String x = "";
                
                boolean autenticado = false;
                while(!autenticado) {
                    boolean inputValido = false; 
                    while (!inputValido) {
                        System.out.println("0 -> Criar conta");
                        System.out.println("1 -> Autenticar");

                        x = keyboard.readLine();
                        if(x.equals("0")) {
                            System.out.println("a criar conta...");

                            inputValido = true;
                        }
                        else if(x.equals("1")) {
                            System.out.println("a autenticar...");
                            inputValido = true;
                        } 
                        else {
                            System.out.println("InputInválido");
                        }
                    } 
                    toServer.println(x);
                    System.out.println("email");
                    String email = keyboard.readLine();
                    toServer.println(email);
                    System.out.println("password");
                    String password = keyboard.readLine();
                    toServer.println(password);
                    
                    String res = fromServer.readLine();
                    System.out.println(res);
                    if(res.equals("Autenticado")) {
                        autenticado = true;
                    }
                }
                
                System.out.println("0 -> Servidor a pedido");
                System.out.println("1 -> Servidor a leilão");
                System.out.println("2 -> Libertar servidor");
                System.out.println("3 -> Consultar conta");
                
                
                
                
                
                
                
		/*String current;
		
		while((current = sin.readLine()) != null){
			out.println(current);
			System.out.println(in.readLine());

		}
		System.out.println("Shutdown Output");
		cs.shutdownOutput();
		System.out.println("Waiting for final result");
		System.out.println("The answer is: " + in.readLine());
		System.out.println("Done");

		in.close();
		out.close();
		sin.close();*/

	}
}
