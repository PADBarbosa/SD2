package projeto;

import java.util.Comparator;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class LicitacaoComparator implements Comparator<Licitacao>{
    
    
    public int compare(Licitacao l1, Licitacao l2) {
        int valorCompare = Float.compare(l1.getValor(), l2.getValor());
        if ( valorCompare == 1) {
            return -1;
        }
        else if (Float.compare(l1.getValor(), l2.getValor()) == -1) {
            return 1;
        }
        else return 0;
    }
}
    