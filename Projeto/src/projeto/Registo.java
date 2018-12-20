/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;


/**
 *
 * @author ze
 */
public class Registo {
    int contador;
    private Servidores large;
    private Servidores medium;
    private Servidores small;

    public Registo() {
        this.contador = 0;
        this.large = new Servidores();
        this.medium = new Servidores();
        this.small = new Servidores();
        adicionaServidor("large");
        adicionaServidor("medium");
        adicionaServidor("medium");
        adicionaServidor("small");
        adicionaServidor("small");
        adicionaServidor("small");
    }
    
    public void adicionaServidor(String tipo) {
        if(tipo.equals("large")){
            this.large.adicionaServidores(this.contador, tipo, Tipos.getPreco(tipo));
        }
        else if(tipo.equals("medium")){
             this.medium.adicionaServidores(this.contador, tipo, Tipos.getPreco(tipo));
        }
        else if(tipo.equals("small")){
             this.small.adicionaServidores(this.contador, tipo, Tipos.getPreco(tipo));
        }
    }
    
    public int reservaPedido(String tipo, String email){
        if(tipo.equals("large")){
            return this.large.reservaPedido(email);
        }
        else if(tipo.equals("medium")){
            return this.medium.reservaPedido(email);
        }
        else if(tipo.equals("small")){
            return this.small.reservaPedido(email);
        }
        return -1;
    }
    
    public int reservaLeilao(String tipo, String email, float valor){
        if(tipo.equals("large")){
            return this.large.reservaLeilao(email, valor);
        }
        else if(tipo.equals("medium")){
            return this.medium.reservaLeilao(email, valor);
        }
        else if(tipo.equals("small")){
            return this.small.reservaLeilao(email, valor);
        }
        return -1;
    }
    
    public void retiraServidor(String tipo, int id){
        if(tipo.equals("large")){
            this.large.libertaServidor(id);
        }
        else if(tipo.equals("medium")){
            this.medium.libertaServidor(id);
        }
        else if(tipo.equals("small")){
            this.small.libertaServidor(id);
        }
    }
}