public class Main {
    public static void main(String[] args) {
        Grafo<Integer> grafo = new Grafo<Integer>();
        grafo.adicionarVertice(1);
        grafo.adicionarVertice(2);
        grafo.adicionarVertice(3);
        grafo.adicionarVertice(4);
        grafo.adicionarVertice(5);
       

        grafo.adicionarAresta(2.0, 1, 2);
        grafo.adicionarAresta(3.0, 2, 4);
        grafo.adicionarAresta(1.0, 4, 3);
        grafo.adicionarAresta(1.0, 1, 3);
        grafo.adicionarAresta(2.0, 5, 2);
        grafo.adicionarAresta(3.0, 5, 1);
    }
}