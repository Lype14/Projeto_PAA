import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Grafo<Integer> grafo = new Grafo<Integer>();
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        grafo.adicionarVertice(5);
       

        grafo.adicionarAresta(2.0,0.0, 1, 2);
        grafo.adicionarAresta(3.0,0.0, 2, 4);
        grafo.adicionarAresta(1.0,0.0, 4, 3);
        grafo.adicionarAresta(1.0,0.0, 1, 3);
        grafo.adicionarAresta(2.0,0.0, 5, 2);
        grafo.adicionarAresta(3.0,0.0, 5, 1);

        grafo.buscaEmLargura(0,3);
    }

    public void edmondsKarp(){ // função que irá calcular o fluxo máximo para a rede

    }

    public <TIPO> Grafo aumentaFluxo(Grafo principal, Map<Vertice<TIPO>, Aresta<TIPO>> caminho,Vertice<TIPO>origem,Vertice<TIPO>destino){
        // Passo 1: Determinar o fluxo máximo possível no caminho (mínimo das capacidades das arestas)
    double fluxoMinimo = Double.MAX_VALUE;
    Vertice<TIPO> atual = destino;

    while (!atual.equals(origem)) {
        Aresta<TIPO> aresta = caminho.get(atual);
        if (aresta == null) {
            System.out.println("Erro: Caminho inválido.");
            return principal;
        }
        fluxoMinimo = Math.min(fluxoMinimo, aresta.getCapacidade());
        atual = aresta.getInicio();
    }
        return principal;
    }
}