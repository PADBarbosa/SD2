/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto;

/**
 *
 * @author ze
 */
public class Servidor {
    private int id;
    private String tipo;
    private float preçoFixo;
    private Boolean ocupado;
    private String ocupante;
    
    public Servidor(int id, String tipo, float preçoFixo) {
        this.id = id;
        this.tipo = tipo;
        this.preçoFixo = preçoFixo;
        this.ocupado = false;
        this.ocupante = "";
    }
    
    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public float getPreçoFixo() {
        return preçoFixo;
    }

    public Boolean getOcupado() {
        return ocupado;
    }

    public String getOcupante() {
        return ocupante;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPreçoFixo(float preçoFixo) {
        this.preçoFixo = preçoFixo;
    }

    public void setOcupado(Boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setOcupante(String ocupante) {
        this.ocupante = ocupante;
    }
    
   public void reserva(String nome) {
       this.setOcupante(nome);
       this.setOcupado(true);
   }
}
