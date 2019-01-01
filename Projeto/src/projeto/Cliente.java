package projeto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */

//Adicionar sincronização?
public class Cliente{
    String email;
    String password;
    float valorDivida;
    Map<Integer, Reserva> reservas;

    public Cliente(String email, float valorDivida, Map<Integer, Reserva> reservas){
        this.email = email;
        this.valorDivida = valorDivida;
        this.reservas = reservas;
    }

    public String getEmail() {
        return email;
    }

    public float getValorDivida() {
        return valorDivida;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setValorDivida(float valorDivida) {
        this.valorDivida = valorDivida;
    }

    public void setReservas(Map<Integer, Reserva> reservas) {
        this.reservas = reservas;
    }
    
    public synchronized void adicionaReservaPedido(Reserva r){
        this.reservas.put(r.getIdReserva(), r);
    }
    
    public synchronized void adicionaReservaLeilao(Reserva r){
        this.reservas.put(r.getIdReserva(), r);
    }
    
    //dataCancelamento -> data em que foi recebido o pedido de cancelamento
    public synchronized float DividaAtual(LocalDateTime dataCancelamento){
        float valor = this.valorDivida;
        for(Reserva r : reservas.values()) {
            LocalDateTime inicio = r.getDataReserva();
            float taxa = r.getTaxa();
            valor += calculaValorIntervalo(inicio, dataCancelamento, taxa);
        }
        return valor;
    }
    
    public synchronized List<String> listaIds(){
        List<String> res = new ArrayList<>();
        for(Reserva r : reservas.values()){
            res.add(r.getIdReserva() + " " + r.getTipo());
        }
        return res;
    }
    //dataCancelamento -> data em que foi recebido o pedido de cancelamento
    public synchronized void cancelaReserva(int id, LocalDateTime dataCancelamento) {
        Reserva r = this.reservas.get(id); 
        LocalDateTime inicio = r.getDataReserva();
        float taxa = r.getTaxa();
        valorDivida += calculaValorIntervalo(inicio, dataCancelamento, taxa);
        this.reservas.remove(id);
    }
   
    public float calculaValorIntervalo(LocalDateTime inicio, LocalDateTime fim, float taxa) {
        Duration duracao = Duration.between(inicio, fim);
        long segundos = duracao.getSeconds();
        return (segundos * (taxa /3600));
    }
   
    public Reserva getReserva(int id) {
        return this.reservas.get(id);
    }
}