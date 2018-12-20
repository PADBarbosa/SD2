/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Barbosa
 */
public class Cliente {
    String email;
    String password;
    float valorDivida;
    Map<Integer, Reserva> reservas;

    public Cliente(String email, float valorDivida, Map<Integer, Reserva> reservas) {
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
    
    public void adicionaReserva(int id, LocalDateTime dataReserva, String tipo){
        float taxa = Tipos.getPreco(tipo);
        Reserva r = new Reserva(id, taxa, dataReserva, tipo);
        this.reservas.put(id, r);
    }
    
    public float valorPagar(int id) {
        Reserva r = reservas.get(id);
        LocalDateTime inicio = r.getDataReserva();
        LocalDateTime atual = LocalDateTime.now();
        Duration duracao = Duration.between(inicio, atual);
        long segundos = duracao.getSeconds();
        float taxa = r.getTaxa();
        return (segundos * taxa);
    }
    
    public List<String> listaIds(){
        List<String> res = new ArrayList<>();
        for(Reserva r : reservas.values()){
            res.add(r.getId() + " " + r.getTipo());
        }
        return res;
    }
    
   // public void cancelaReserva(int id)
    
}