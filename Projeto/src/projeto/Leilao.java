/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.List;

/**
 *
 * @author Barbosa
 */
public class Leilao {
    private List<Cliente> licitacoesGrandes;
    private List<Cliente> licitacoesMedios;
    private List<Cliente> licitacoesPequenos;
   // lista de licitacoes para servidores grandes 
   // lista de licitacoes para servidores medios
   // lista de licitacoes para servidores pequenos
            
    
    public String ganhaLicitacao(List<Licitacao> licitacoes){
        String res = new String();
        float v = 0;
        for(Licitacao l : licitacoes){
            if(l.getValor() > v) res = l.getCliente();
        }
        return res;
    }
   // quando for para acordar os clientes que fizeram a licitacao primeiro ele vai procurar na lista dos pedidos individuais
   // se não encontrar vai para a lista correspondente dos leiloes
   // criar uma variavel e guardar nela o nome do cliente que ganha a licitacao para aquele tipo de servidor
   // comparar o nome de cada thread com a vaariavel de quem ganhou, se a thread corresponder ao nome continuar senão adormece
            
    


}
