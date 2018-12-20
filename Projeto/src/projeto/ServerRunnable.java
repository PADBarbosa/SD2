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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ze
 */
class ServerRunnable implements Runnable{
	Socket cs;
        Autenticacao autenticacao;
        Registo registo;
        Clientes clientes;

	ServerRunnable(Socket clientSocket, Autenticacao autenticacao, Registo registo, Clientes clientes){
		this.cs = clientSocket;
                this.autenticacao = autenticacao;
                this.registo = registo;
                this.clientes = clientes;
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
                    String email = "";
                    String password = "";
                    String x = "";
                    
                    while(!autenticado) {
                        x = in.readLine();
                        email = in.readLine();
                        password = in.readLine();
                        System.out.println(x);
                        System.out.println(email);
                        System.out.println(password);
                        if(x.equals("0")) {
                            sucesso = this.autenticacao.registaUser(email, password);
                            if (sucesso) {
                                Cliente c = new Cliente(email, 0, new HashMap<>());
                                clientes.adicionaCliente(c);
                                out.println("Conta criada com sucesso");
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
                    while(true){
                        x = in.readLine();
                        System.out.println(x);
                        System.out.println("apos registo");
                        
                        if(x.equals("0")){
                            String tipo = in.readLine();
                            System.out.println(tipo);
                            System.out.println("dentro if");
                            int res = registo.reservaPedido(tipo, email);
                            System.out.println("reservado");
                            System.out.println(res);
                            if(res == -1){
                                out.println("Deu merda jovem");
                            }
                            else{
                                Cliente c = clientes.getPorEmail(email);
                                c.adicionaReserva(res, LocalDateTime.now(), tipo);
                                out.println("Alocado servidor número: " + res);
                            }
                        }
                        else if(x.equals("2")){
                            Cliente c = clientes.getPorEmail(email);
                            List<String> reservas = c.listaIds();
                            for(String s : reservas){
                                out.println(s);
                            }
                            String id = in.readLine();
                            String tipo = in.readLine();
                            registo.retiraServidor(tipo , Integer.parseInt(id));
                        }
                        
                        else if(x.equals("3")){
                            Cliente c = clientes.getPorEmail(email);
                            float valorDivida = c.getValorDivida();
                            System.out.println("divida" + valorDivida);
                            out.println("Valor em divida: " + valorDivida);
                        }
                    }
		} catch (Exception e){
                    System.out.println("Asneira");
                    e.printStackTrace();
                }
	}
}
