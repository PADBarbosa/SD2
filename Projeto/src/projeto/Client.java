package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Client {
    
    public static void servidorPedido(PrintWriter toServer, BufferedReader keyboard) throws IOException{
        System.out.println("Escolha o tipo de servidor: ");
        for(String s : Tipos.getTipos()) {
            System.out.println(s);
        }
        String tipo = keyboard.readLine();
        toServer.println("0");
        toServer.println(tipo);
    }
       
    public static void servidorLeilao(PrintWriter toServer, BufferedReader keyboard) throws IOException{
        System.out.println("Escolha o tipo de servidor: ");
        for(String s : Tipos.getTipos()) {
            System.out.println(s);
        }
        String tipo = keyboard.readLine();
        System.out.println("Indique o valor da licitação(€/hora): ");
        String valor = keyboard.readLine();
        toServer.println("1");
        toServer.println(tipo);
        toServer.println(valor);
    }
    
    public static void libertaServidor(PrintWriter toServer, BufferedReader fromServer, BufferedReader keyboard) throws IOException{
        toServer.println("2");
        System.out.println("Indique o Id do servidor a retirar: ");  
        String id = keyboard.readLine();
        toServer.println(id);
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
                System.out.println("\n0 -> Criar conta");
                System.out.println("1 -> Autenticar");

                x = keyboard.readLine();
                if(x.equals("0")) {
                    System.out.println("\nA criar conta...");
                    inputValido = true;
                }
                else if(x.equals("1")) {
                    System.out.println("\nA autenticar...");
                    inputValido = true;
                } 
                else {
                    System.out.println("Input Inválido");
                }
            } 
                    
            toServer.println(x);
                    
            System.out.println("Email");
            String email = keyboard.readLine();
            toServer.println(email);
                    
            System.out.println("Password");
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
                
        boolean m = true;
        while(m){
            System.out.println("\n0 -> Servidor a pedido");
            System.out.println("1 -> Servidor a leilão");
            System.out.println("2 -> Libertar servidor");
            System.out.println("3 -> Consultar conta");
            System.out.println("4 -> Sair");

            x = keyboard.readLine();
            if(x.equals("0")){
                servidorPedido(toServer, keyboard);
            }
            else if(x.equals("1")){
                servidorLeilao(toServer, keyboard);
            }
            else if(x.equals("2")){
                libertaServidor(toServer, fromServer, keyboard);
            }
            else if(x.equals("3")){
                consultaDivida(toServer);
            }
            else if(x.equals("4")){
                toServer.println("4");
                m = false;
            }   
        }
    }
}

class ServerToClient implements Runnable{
    BufferedReader le;
    
    public ServerToClient(BufferedReader in){
        le = in;
    }

    @Override
    public void run(){
        try{
            String message;
            while((message = le.readLine()) != null){
                System.out.println(message);
            }
            le.close();
        }catch(Exception e){
            System.out.println("Erro cliente");
            e.printStackTrace();
        }
    }
}

