package projeto;

/**
 *
 * Classe que representa uma licitação.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Licitacao {
    private float valor;
    private int id;

    public Licitacao(float valor, int id) {
        this.valor = valor;
        this.id = id;
    }
    
    public float getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass()) return false;

        Licitacao l = (Licitacao) o;
        return this.valor == l.getValor() &&
               this.id == l.getId();
    }
}
