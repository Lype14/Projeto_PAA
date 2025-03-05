import java.util.*;

public class Grafo<TIPO> {

    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafo(){
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();

    }

    public void adicionarVertice(TIPO dado){
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double capacidade, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);

        if (inicio != null && fim != null) {
            Aresta<TIPO> arestaNormal = new Aresta<TIPO>(capacidade, inicio, fim);
            Aresta<TIPO> arestaReversa = new Aresta<TIPO>(0.0, fim, inicio); // Aresta reversa começa com capacidade 0

            arestaNormal.setReversa(arestaReversa);
            arestaReversa.setReversa(arestaNormal);

            inicio.adicionarArestaSaida(arestaNormal);
            fim.adicionarArestaEntrada(arestaNormal);

            fim.adicionarArestaSaida(arestaReversa);
            inicio.adicionarArestaEntrada(arestaReversa);

            this.arestas.add(arestaNormal);
            this.arestas.add(arestaReversa);
        } else {
            System.out.println("Alguns dos vértices não existem no grafo");
        }
    }


    public Vertice<TIPO> getVertice(TIPO dado){
        Vertice<TIPO> vertice = null;
        for(int i = 0; i< this.vertices.size(); i++){
            if(this.vertices.get(i).getDado().equals(dado)){
                vertice = this.vertices.get(i);
                break;
            }
        }
        return vertice;
    }

    public void buscaEmLargura(TIPO dadoInicio) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        if (inicio == null) {
            System.out.println("Vértice inicial não encontrado.");
            return;
        }

        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<>();

        marcados.add(inicio);
        System.out.println("Iniciando BFS a partir do vértice: " + inicio.getDado());
        fila.add(inicio);

        while (!fila.isEmpty()) {
            Vertice<TIPO> visitado = fila.remove(0);

            for (Aresta<TIPO> aresta : visitado.getArestasSaida()) {
                Vertice<TIPO> proximo = aresta.getFim();
                if (!marcados.contains(proximo)) { // Se o vértice ainda não foi visitado
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
        }
    }


    public boolean buscaCaminhoAumentante(TIPO origem, TIPO destino, Map<Vertice<TIPO>, Aresta<TIPO>> caminho) {
        Queue<Vertice<TIPO>> fila = new LinkedList<>();
        Set<Vertice<TIPO>> visitados = new HashSet<>();

        Vertice<TIPO> origemVertice = getVertice(origem);
        Vertice<TIPO> destinoVertice = getVertice(destino);

        fila.add(origemVertice);
        visitados.add(origemVertice);

        // Enquanto a fila não estiver vazia, continuamos a busca
        while (!fila.isEmpty()) {
            // Remove o primeiro vértice da fila para processar
            Vertice<TIPO> atual = fila.poll();

            // Percorre todas as arestas de saída do vértice atual
            for (Aresta<TIPO> aresta : atual.getArestasSaida()) {
                // Verifica se a aresta ainda tem capacidade residual disponível
                // e se o vértice de destino ainda não foi visitado
                if (aresta.getCapacidadeResidual() > 0 && !visitados.contains(aresta.getFim())) {

                    // Registra no mapa 'caminho' qual aresta levou até esse vértice
                    caminho.put(aresta.getFim(), aresta);

                    // Marca o vértice de destino como visitado
                    visitados.add(aresta.getFim());

                    // Adiciona o vértice de destino à fila para explorar suas conexões depois
                    fila.add(aresta.getFim());

                    // Se o vértice de destino for o vértice final da busca, encontramos um caminho aumentante
                    if (aresta.getFim().equals(destinoVertice)) {
                        return true; // Retorna verdadeiro, pois há um caminho aumentante disponível
                    }
                }
            }
        }

// Se a fila esvaziar sem encontrar o destino, não há caminho aumentante disponível
        return false;

    }

}
