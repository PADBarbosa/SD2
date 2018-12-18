/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

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
    String nome;
    String email;
    String password;
    float valorDivida;
    LocalDate dataRegisto;
    Map<Integer, Reserva> reservas;

    public Cliente(String nome, String email, String password, float valorDivida, LocalDate dataRegisto, Map<Integer, Reserva> reservas) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.valorDivida = valorDivida;
        this.dataRegisto = dataRegisto;
        this.reservas = reservas;
    }
    
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getValorDivida() {
        return valorDivida;
    }

    public LocalDate getDataRegisto() {
        return dataRegisto;
    }

    public Map<Integer, Reserva> getReservas() {
        return reservas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValor(float valorDivida) {
        this.valorDivida = valorDivida;
    }

    public void setValorDivida(float valorDivida) {
        this.valorDivida = valorDivida;
    }

    public void setDataRegisto(LocalDate dataRegisto) {
        this.dataRegisto = dataRegisto;
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
    
}