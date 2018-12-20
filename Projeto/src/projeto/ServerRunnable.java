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
public class ServerRunnable implements Runnable{
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
                            new Thread(new ThreadPedido(tipo, email, out, this.registo, this.clientes)).start();
                        }
                        else if(x.equals("1")){
                            String tipo = in.readLine();
                            float valor = Float.parseFloat(in.readLine());
                            new Thread(new ThreadLeilao(tipo, email, valor, out, this.registo, this.clientes)).start();
                            
                        }
                        else if(x.equals("2")){
                            Cliente c = clientes.getPorEmail(email);
                            List<String> reservas = c.listaIds();
                            for(String s : reservas){
                                out.println(s);
                            }
                            int id = Integer.parseInt(in.readLine());
                            String tipo = in.readLine();
                            new Thread(new ThreadLiberta(this.registo, id, tipo, c, out)).start();
                        }
                        
                        else if(x.equals("3")){
                            new Thread(new ThreadConsulta(email, this.clientes, out)).start();
                        }
                        else if(x.equals("4")){
                            out.close();
                        }
                    }
		} catch (Exception e){
                    System.out.println("Asneira");
                    e.printStackTrace();
                }
	}
}



class ThreadPedido implements Runnable{
    private String tipo;
    private String email;
    private PrintWriter out;
    private Registo registo;
    private Clientes clientes;

    public ThreadPedido(String tipo, String email, PrintWriter out, Registo registo, Clientes clientes) {
        this.tipo = tipo;
        this.email = email;
        this.out = out;
        this.registo = registo;
        this.clientes = clientes;
    }
        
    @Override
    public void run() {
        int res = registo.reservaPedido(tipo, email);
        if(res == -1){
            out.println("Erro");
        }
        else{
            Cliente c = clientes.getPorEmail(email);
            c.adicionaReservaPedido(res, LocalDateTime.now(), tipo);
            out.println("Alocado servidor número: " + res);
        }
        
    }
    
}








class ThreadLeilao implements Runnable{
    private String tipo;
    private String email;
    private float valor;
    private Registo registo;
    private Clientes clientes;
    private PrintWriter out;

    public ThreadLeilao(String tipo, String email, float valor, PrintWriter out, Registo registo, Clientes clientes) {
        this.tipo = tipo;
        this.email = email;
        this.out = out;
        this.registo = registo;
        this.clientes = clientes;
        this.valor = valor;
    }
        
    @Override
    public void run() {
        int res = registo.reservaLeilao(tipo, email, valor);
        Cliente c = clientes.getPorEmail(email);
        c.adicionaReservaLeilao(res, LocalDateTime.now(), tipo, valor);
        out.println("Alocado servidor número: " + res);
    }
    
}










class ThreadLiberta implements Runnable{
    private Registo registo;
    private Integer id;
    private String tipo;
    private Cliente cliente;
    private PrintWriter out;
    

    public ThreadLiberta(Registo registo, Integer id, String tipo, Cliente cliente, PrintWriter out) {
        this.registo = registo;
        this.id = id;
        this.tipo = tipo;
        this.cliente = cliente;
        this.out = out;
    }
        
    @Override
    public void run() {
        this.registo.retiraServidor(tipo , id);
        this.cliente.cancelaReserva(id);
    }
    
}



class ThreadConsulta implements Runnable{
    private String email;
    private Clientes clientes;
    private PrintWriter out;
    

    public ThreadConsulta(String email, Clientes clientes, PrintWriter out) {
        this.email = email;
        this.out = out;
        this.clientes = clientes;
    }
        
    @Override
    public void run() {
        Cliente c = clientes.getPorEmail(email);
        float valorDivida = c.valorPagar();
        out.println("Valor em divida: " + valorDivida);
    }
    
}