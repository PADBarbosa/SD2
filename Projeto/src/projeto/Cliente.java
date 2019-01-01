package projeto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * Classe que guarda toda a informação relativa a um cliente.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
//Adicionar sincronização?
public class Cliente{
    String email;
    String password;
    float valorDivida;
    /** O map guarda nas keys o ID da reserva e no valor a reserva corresponde a esse ID */
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
    
    /**
     * Função que adiciona uma reserva feita por pedido.
     * @param r 
     */
    public synchronized void adicionaReservaPedido(Reserva r){
        this.reservas.put(r.getIdReserva(), r);
    }
    
    /**
     * Função que adiciona uma reserva feita por leilão.
     * @param r 
     */
    public synchronized void adicionaReservaLeilao(Reserva r){
        this.reservas.put(r.getIdReserva(), r);
    }
    
    /**
     * Função que calcula o valor a pagar por um utilizador devido aos servidores que reservou. 
     * O valor é calculado no momento do pedido.
     * @param dataCancelamento
     * @return Valor da dívida
     */
    public synchronized float DividaAtual(LocalDateTime dataCancelamento){
        float valor = this.valorDivida;
        for(Reserva r : reservas.values()) {
            LocalDateTime inicio = r.getDataReserva();
            float taxa = r.getTaxa();
            valor += calculaValorIntervalo(inicio, dataCancelamento, taxa);
        }
        return valor;
    }
    
    /**
     * Funçção que retorna a lista de ID's de todas as reservas feitas pelo cliente.
     * @return Lista de reservas
     */
    public synchronized List<String> listaIds(){
        List<String> res = new ArrayList<>();
        for(Reserva r : reservas.values()){
            res.add(r.getIdReserva() + " " + r.getTipo());
        }
        return res;
    }
    
    /**
     * Função que cancela a reserva de um servidor e que calcula o custo da reserva
     * dependendo da duração.
     * @param id
     * @param dataCancelamento 
     */
    public synchronized void cancelaReserva(int id, LocalDateTime dataCancelamento) {
        Reserva r = this.reservas.get(id); 
        LocalDateTime inicio = r.getDataReserva();
        float taxa = r.getTaxa();
        valorDivida += calculaValorIntervalo(inicio, dataCancelamento, taxa);
        this.reservas.remove(id);
    }

    /**
     * Função que calcula o custo da reserva de um servidor num dado intervalo de tempo.
     * @param inicio
     * @param fim
     * @param taxa
     * @return Custo da reserva
     */
    public float calculaValorIntervalo(LocalDateTime inicio, LocalDateTime fim, float taxa) {
        Duration duracao = Duration.between(inicio, fim);
        long segundos = duracao.getSeconds();
        return (segundos * (taxa /3600));
    }
    
    /**
     * Função que devolve a reserva correspondente ao ID passado como argumento
     * @param id
     * @return Reserva 
     */
    public Reserva getReserva(int id) {
        return this.reservas.get(id);
    }
}