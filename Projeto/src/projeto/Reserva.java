package projeto;

import java.time.LocalDateTime;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Reserva {
    //id do servidor
    int id;
    float taxa;
    LocalDateTime dataReserva;
    String tipo;

    public Reserva(int id, float taxa, LocalDateTime dataReserva, String tipo) {
        this.id = id;
        //por hora
        this.taxa = taxa;
        this.dataReserva = dataReserva;
        this.tipo = tipo;
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

    public String getTipo() {
        return tipo;
    }
/*
    public void setId(int id) {
        this.id = id;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }*/
}
