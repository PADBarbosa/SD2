package projeto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Classe que contém os diferentes tipos e preços dos servidores.
 * 
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public final class Tipos {
    
    static float largePrice = (float) 0.50;
    static float mediumPrice = (float) 0.30;
    static float smallPrice = (float) 0.15;
    static final List<Tipo> tipos = new ArrayList<Tipo>() {{
        add(new Tipo("large", 15));
        add(new Tipo("medium", 10));
        add(new Tipo("small", 5));
    }};
    static final List<String> st = new ArrayList<String>() {{
        for (Tipo i : tipos) {       //parar o ciclo quando encontrar?
            add(i.getTipo());
        }
    }}; 
        
    public static float getTaxa(String tipo){
        float taxa = 0;
        for (Tipo i : tipos) {       //parar o ciclo quando encontrar?
            if (i.getTipo().equals(tipo)) {
                taxa = i.getTaxa(); 
            }
        }
        return taxa;
    }
    
    public static List<String> getTipos() {
        return st;                        //clone?
    }
    
    public static boolean contains(String tipo) {
        System.out.println(st.contains(tipo) + tipo);
        return st.contains(tipo);
    }
}
   
class Tipo {
    private String tipo;
    private float taxa;

    public Tipo(String tipo, float taxa) {
        this.tipo = tipo;
        this.taxa = taxa;
    }

    public String getTipo() {
        return tipo;
    }

    public float getTaxa() {
        return taxa;
    }
}