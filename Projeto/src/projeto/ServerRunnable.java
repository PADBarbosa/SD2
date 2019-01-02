package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Classe correspondente à thread que vai lidar com os pedidos do cliente.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class ServerRunnable implements Runnable{
    Socket cs;
    Autenticacao autenticacao;
    Registo registo;
    Clientes clientes;

    public ServerRunnable(Socket clientSocket, Autenticacao autenticacao, Registo registo, Clientes clientes){
        this.cs = clientSocket;
        this.autenticacao = autenticacao;
        this.registo = registo;
        this.clientes = clientes;
    }

    @Override
    public void run() {
        try{
            PrintWriter out = new PrintWriter(this.cs.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.cs.getInputStream()));

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
                
                // Criar conta
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
                // Autenticar
                else if(x.equals("1")) {
                    sucesso = this.autenticacao.verificaUser(email, password);
                    if (sucesso) {
                        out.println("Autenticado");
                        autenticado = true;
                    }
                    else {
                        out.println("Email ou Password errados");
                    }
                }                        
            }
            
            boolean sair = false;
            
            while(!sair){
                x = in.readLine();
                System.out.println(x);

                // Reservar um servidor a pedido
                if(x.equals("0")){
                    String tipo = (in.readLine()).toLowerCase();
                    if (!Tipos.contains(tipo)) {
                        out.println("Tipo " + tipo + " não existe");
                    }
                    else {
                        new Thread(new ThreadPedido(tipo, email, out, this.registo, this.clientes)).start();
                    }
                }
                
                // Rservar um servidor a leilão
                else if(x.equals("1")){
                    String tipo = (in.readLine()).toLowerCase();
                    if (!Tipos.contains(tipo)) {
                        out.println("Tipo " + tipo + " não existe");
                    }
                    else {
                        float valor = Float.parseFloat(in.readLine());
                        if (valor > 0) {
                            new Thread(new ThreadLeilao(tipo, email, valor, out, this.registo, this.clientes)).start();
                        }
                        else {
                             out.println("Licitação tem que ser superior a 0");
                        }
                    }
                }
                
                // Libertar um servidor
                else if(x.equals("2")){
                    Cliente c = clientes.getPorEmail(email);
                    List<String> reservas = c.listaIds();
                    if(reservas.isEmpty()) {
                        out.println("Não existem reservas (introduza 'r' para retroceder)");
                    }
                    else {                    
                        for(String s : reservas){
                            out.println(s);
                        }
                        try{
                            int idReserva = Integer.parseInt(in.readLine());
                            if(c.containsReserva(idReserva)){
                                LocalDateTime atual = LocalDateTime.now();
                                new Thread(new ThreadLiberta(this.registo, idReserva, c, out, atual)).start();
                            }
                            else{
                                out.println("Reserva com esse ID não existe");
                            }
                        }catch(IOException | NumberFormatException e){
                            out.println("Número não válido");
                        }
                    }
                }
                
                // Consultar conta
                else if(x.equals("3")){
                    LocalDateTime atual = LocalDateTime.now();
                    new Thread(new ThreadConsulta(email, this.clientes, out, atual)).start();
                }
                
                // Sair
                else if(x.equals("4")){
                    out.close();
                    sair = true;
                }
            }
        }catch (Exception e){
            System.out.println("Asneira");
            e.printStackTrace();
        }
    }
}

/**
 *
 * Thread criada quando o cliente pretende reservar um servidor a pedido.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
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
        Reserva r = registo.reservaPedido(tipo, email);
        Cliente c = clientes.getPorEmail(email);
        c.adicionaReservaPedido(r);
        out.println("Alocado servidor número: " + r.getIdServidor());
    }
}

/**
 *
 * Thread criada quando o cliente pretende reservar um servidor a leilão.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
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
        Reserva r = registo.reservaLeilao(tipo, email, valor);
        Cliente c = clientes.getPorEmail(email);
        (new Thread(new ThreadEspera(r, tipo, email, out, registo, c ))).start();
        c.adicionaReservaLeilao(r);
        out.println("Alocado servidor número: " + r.getIdServidor());
    }
}

/**
 *
 * Thread criada quando o cliente pretende libertar um dos servidores que reservou anteriormente.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
class ThreadLiberta implements Runnable{
    private Registo registo;
    private int idReserva; //id da reserva
    private Cliente cliente;
    private PrintWriter out;
    private LocalDateTime dataPedido;
    

    public ThreadLiberta(Registo registo, int idReserva, Cliente cliente, PrintWriter out, LocalDateTime dataPedido) {
        this.registo = registo;
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.out = out;
        this.dataPedido = dataPedido;
    }
        
    @Override
    public void run() {
        Reserva r = this.cliente.getReserva(idReserva);
        int idServidor = r.getIdServidor();
        String t = r.getTipo();
        this.registo.retiraServidor(t, idServidor);
        this.cliente.cancelaReserva(idReserva, dataPedido);
        out.println("Servidor libertado");
    }
}

/**
 *
 * Thread criada quando o cliente pretende consultar o valor a pagar até ao momento do pedido.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
class ThreadConsulta implements Runnable{
    private String email;
    private Clientes clientes;
    private PrintWriter out;
    private LocalDateTime dataPedido;

    
    public ThreadConsulta(String email, Clientes clientes, PrintWriter out, LocalDateTime dataPedido) {
        this.email = email;
        this.out = out;
        this.clientes = clientes;
        this.dataPedido = dataPedido;
    }
        
    @Override
    public void run() {
        Cliente c = clientes.getPorEmail(email);
        for(Reserva r : (c.getReservas()).values()){
            out.println("Reserva nº " + r.getIdReserva() + " do tipo " + r.getTipo() + " alocada em " + r.getDataReserva());
        }
        float valorDivida = c.DividaAtual(dataPedido);
        String formatada = String.format( "%.2f", valorDivida);
        out.println("Valor em dívida " + formatada);
    }
}

/**
 *
 * Thread criada para aguardar uma resposta quando não é possivel reservar um servidor ou quando é reservado um servidor a leilão.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
class ThreadEspera implements Runnable{
    private Reserva reserva;
    private String tipo;
    private String email;
    private PrintWriter out;
    private Registo r;
    private Cliente c;

    public ThreadEspera(Reserva reserva,String tipo, String email, PrintWriter out, Registo r, Cliente c) {
        this.reserva = reserva;
        this.tipo = tipo;
        this.email = email;
        this.out = out;
        this.r = r;
        this.c = c;
    }
    
    @Override
    public void run() {
        System.out.println("run com sucesso");
        this.r.esperaPerderLeilao(tipo, reserva.getIdServidor(), email);
        LocalDateTime atual = LocalDateTime.now();
        c.cancelaReserva(reserva.getIdReserva(), atual);
        out.println("Reserva " + reserva.getIdReserva() + " de leilão libertada");
    }
}