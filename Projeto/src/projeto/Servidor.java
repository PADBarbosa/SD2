/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

import java.time.LocalDateTime;

/**
 *
 * @author ze
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
    
   public void reserva(String nome) {
       this.setCliente(nome);
       this.setOcupado(true);
       this.dataInicio = LocalDateTime.now();
   }
   
   public void liberta() {
       this.cliente = "";
       this.ocupado = false;
       
   }
}
