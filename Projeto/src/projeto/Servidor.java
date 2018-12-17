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
    private String nome;
    private String tipo;
    private float preçoFixo;
    private Boolean ocupado;
    private String ocupante;
    
    public Servidor(String nome, String tipo, float preçoFixo) {
        this.nome = nome;
        this.tipo = tipo;
        this.preçoFixo = preçoFixo;
        this.ocupado = false;
        this.ocupante = "";
    }
    
    public String getNome() {
        return nome;
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

    public void setNome(String nome) {
        this.nome = nome;
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
