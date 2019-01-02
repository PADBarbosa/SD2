package projeto;

import java.time.LocalDateTime;

/**
 *
 * Classe que contém toda a informação referente às reservas efetuadas.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Reserva {
    int idReserva;
    int idServidor;
    float taxa;
    LocalDateTime dataReserva;
    String tipo;

    public Reserva(int idReserva, int idServidor , float taxa, LocalDateTime dataReserva, String tipo) {
        this.idReserva = idReserva;
        this.idServidor = idServidor;
        //por hora
        this.taxa = taxa;
        this.dataReserva = dataReserva;
        this.tipo = tipo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdServidor() {
        return idServidor;
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
}
