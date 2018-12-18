/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.time.LocalDateTime;

/**
 *
 * @author jose9
 */
public class Reserva {
    //id do servidor
    int id;
    float taxa;
    LocalDateTime dataReserva;

    public Reserva(int id, float taxa, LocalDateTime dataReserva) {
        this.id = id;
        this.taxa = taxa;
        this.dataReserva = dataReserva;
    }

    public int getId() {
        return id;
    }

    public float getTaxa() {
        return taxa;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }
    
    
    
}
