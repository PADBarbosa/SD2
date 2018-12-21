package projeto;

import java.util.Comparator;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class LicitacaoComparator implements Comparator<Licitacao>{
    
    
    public int compare(Licitacao l1, Licitacao l2) {
        return Float.compare(l1.getValor(), l2.getValor());
    }
}
    