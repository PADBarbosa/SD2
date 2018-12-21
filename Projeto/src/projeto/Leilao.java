package projeto;

import java.util.List;

/**
 *
 * @author José Pinto (A81317); Luís Correia (A81141); Pedro Barbosa (A82068)
 */
public class Leilao {
    private List<Licitacao> licitacoesGrandes;
    private List<Licitacao> licitacoesMedios;
    private List<Licitacao> licitacoesPequenos;
   // lista de licitacoes para servidores grandes 
   // lista de licitacoes para servidores medios
   // lista de licitacoes para servidores pequenos

    public Leilao(List<Licitacao> licitacoesGrandes, List<Licitacao> licitacoesMedios, List<Licitacao> licitacoesPequenos) {
        this.licitacoesGrandes = licitacoesGrandes;
        this.licitacoesMedios = licitacoesMedios;
        this.licitacoesPequenos = licitacoesPequenos;
    }

    public List<Licitacao> getLicitacoesGrandes() {
        return licitacoesGrandes;
    }

    public List<Licitacao> getLicitacoesMedios() {
        return licitacoesMedios;
    }

    public List<Licitacao> getLicitacoesPequenos() {
        return licitacoesPequenos;
    }

    public void setLicitacoesGrandes(List<Licitacao> licitacoesGrandes) {
        this.licitacoesGrandes = licitacoesGrandes;
    }

    public void setLicitacoesMedios(List<Licitacao> licitacoesMedios) {
        this.licitacoesMedios = licitacoesMedios;
    }

    public void setLicitacoesPequenos(List<Licitacao> licitacoesPequenos) {
        this.licitacoesPequenos = licitacoesPequenos;
    }
    
   /* public String ganhaLicitacao(List<Licitacao> licitacoes){
        String res = new String();
        float v = 0;
        for(Licitacao l : licitacoes){
            if(l.getValor() > v) res = l.getId();
        }
        return res;
    }*/
   // quando for para acordar os clientes que fizeram a licitacao primeiro ele vai procurar na lista dos pedidos individuais
   // se não encontrar vai para a lista correspondente dos leiloes
   // criar uma variavel e guardar nela o nome do cliente que ganha a licitacao para aquele tipo de servidor
   // comparar o nome de cada thread com a vaariavel de quem ganhou, se a thread corresponder ao nome continuar senão adormece
            
    


}
