import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Grafo<Integer> grafo = new Grafo<>();

        // Criando vértices
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        grafo.adicionarVertice(5);

        // Criando arestas com capacidades
        grafo.adicionarAresta(2.0, 1, 2);
        grafo.adicionarAresta(3.0, 2, 4);
        grafo.adicionarAresta(1.0, 4, 3);
        grafo.adicionarAresta(1.0, 1, 3);
        grafo.adicionarAresta(2.0, 5, 2);
        grafo.adicionarAresta(3.0, 5, 1);

        // Testando a busca em largura
        System.out.println("Busca em largura a partir do vértice 1:");
        grafo.buscaEmLargura(1);

        // Testando a busca por caminho aumentante na rede residual
        Map<Vertice<Integer>, Aresta<Integer>> caminho = new HashMap<>();
        boolean caminhoEncontrado = grafo.buscaCaminhoAumentante(1, 4, caminho);

        if (caminhoEncontrado) {
            System.out.println("Caminho aumentante encontrado!");
            Vertice<Integer> destino = grafo.getVertice(3);
            while (caminho.containsKey(destino)) {
                Aresta<Integer> aresta = caminho.get(destino);
                System.out.println("De " + aresta.getInicio().getDado() + " para " + destino.getDado());
                destino = aresta.getInicio();
            }
        } else {
            System.out.println("Nenhum caminho aumentante encontrado.");
        }
    }
}
