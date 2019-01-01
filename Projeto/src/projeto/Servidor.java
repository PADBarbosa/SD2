package projeto;

import java.time.LocalDateTime;

/**
 *
 * Classe que contém toda a informação referente a um servidor.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Servidor {
    private int id;
    private String tipo;
    private float precoFixo;
    private Boolean ocupado;
    private String cliente;
    private LocalDateTime dataInicio;
    
    public Servidor(int id, String tipo, float preçoFixo) {
        this.id = id;
        this.tipo = tipo;
        this.precoFixo = preçoFixo;
        this.ocupado = false;
        this.cliente = "";
        this.dataInicio = null;
    }
    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public float getPrecoFixo() {
        return precoFixo;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPrecoFixo(float precoFixo) {
        this.precoFixo = precoFixo;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Função que define o servidor como ocupado por um certo cliente.
     * @param email 
     */
    public synchronized void reserva(String email) {
        this.setCliente(email);
        this.setOcupado(true);
        this.dataInicio = LocalDateTime.now();
    }
   
    /**
     * Função que liberta o servidor.
     */
    public synchronized void liberta() {
        this.cliente = "";
        this.ocupado = false;
    }
}
