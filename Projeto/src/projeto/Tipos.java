/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author Barbosa
 */
public final class Tipos {
    static float bigPrice = (float) 0.50;
    static float mediumPrice = (float) 0.30;
    static float smallPrice = (float) 0.15;
        
    public static float getPreco(String tipo){
        if(tipo.equals("big")){
            return bigPrice;
        }
        else if(tipo.equals("medium")){
            return mediumPrice;
        }
        else if(tipo.equals("small")){
            return smallPrice;
        }
        return -1;
    }
}
