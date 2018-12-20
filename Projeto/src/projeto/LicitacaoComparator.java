/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.Comparator;

/**
 *
 * @author Barbosa
 */
public class LicitacaoComparator implements Comparator<Licitacao>{
    
    
    public int compare(Licitacao l1, Licitacao l2) {
        return Float.compare(l1.getValor(), l2.getValor());
    }
}
    