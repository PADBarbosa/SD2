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
    
    public static void servidorPedido(PrintWriter toServer, BufferedReader keyboard) throws IOException{
        System.out.println("Escolha o tipo de servidor");
        System.out.println("Large");
        System.out.println("Medium");
        System.out.println("Small");
        
        String tipo = keyboard.readLine();
        toServer.println("0");
        toServer.println(tipo);
    }
    
    public static void libertaServidor(PrintWriter toServer, BufferedReader fromServer, BufferedReader keyboard) throws IOException{
        toServer.println("2");
        String s = fromServer.readLine();
        System.out.println(s);
        int num = Integer.parseInt(s);
        System.out.println(num);
        for(int i = 0; i < num; i++){
            //System.out.println(fromServer.readLine());
        }
        System.out.println("Indique o Id do servidor a retirar");  
        String id = keyboard.readLine();
        toServer.println(id);
        System.out.println("Indique o tipo do servidor a retirar");
        String tipo = keyboard.readLine();
        toServer.println(tipo);
    }
    
    public static void consultaDivida(PrintWriter toServer)throws IOException{
        toServer.println("3");
    }
    
  
    
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

                ServerToClient sc = new ServerToClient(fromServer);
                Thread t = new Thread(sc);
                t.start();
                
                //verificar condiçao while
                boolean m = true;
                while(m){
                    System.out.println("0 -> Servidor a pedido");
                    System.out.println("1 -> Servidor a leilão");
                    System.out.println("2 -> Libertar servidor");
                    System.out.println("3 -> Consultar conta");
                    System.out.println("4 -> Sair");

                    x = keyboard.readLine();
                    if(x.equals("0")){
                        servidorPedido(toServer, keyboard);
                    }
                    else if(x.equals("1")){

                    }
                    else if(x.equals("2")){
                        libertaServidor(toServer, fromServer, keyboard);
                    }
                    else if(x.equals("3")){
                        consultaDivida(toServer);
                    }
                    else if(x.equals("4")){
                        m = false;
                    }
                    
                }
	}
}

class ServerToClient implements Runnable{
    BufferedReader le;
    PrintWriter escreve;
    
    public ServerToClient(BufferedReader in){
        le = in;
    }

    public void run(){
        try{
            String message;
            while((message = le.readLine()) != null){
                System.out.println(message);
            }
            escreve.close();
            le.close();
	}catch(Exception e){System.out.println("Erro cliente");}
    }
}
