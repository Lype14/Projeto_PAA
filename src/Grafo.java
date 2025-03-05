import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void adicionarAresta(Double capacidade, Double fluxo, TIPO dadoInicio, TIPO dadoFim){
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        if(inicio != null && fim != null){
            Aresta<TIPO> aresta = new Aresta<TIPO>(capacidade, fluxo,inicio,fim);
            inicio.adicionarArestaSaida(aresta);
            fim.adicionarArestaEntrada(aresta);
            this.arestas.add(aresta);
        }else{
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

  public Map<Vertice<TIPO>, Aresta<TIPO>> buscaEmLargura(int S, int T) {
    ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
    ArrayList<Vertice<TIPO>> fila = new ArrayList<>();
    Map<Vertice<TIPO>, Aresta<TIPO>> caminho = new HashMap<>(); // Armazena o caminho
    
    Vertice<TIPO> origem = this.vertices.get(S);
    Vertice<TIPO> atual = this.vertices.get(S);
    Vertice<TIPO> destino = this.vertices.get(T);
    
    marcados.add(atual);
    fila.add(atual);
    caminho.put(atual, null); // O nó de origem não arestas incidentes

    while (!fila.isEmpty()) {
        Vertice<TIPO> visitado = fila.get(0);
        fila.remove(0);

        // Se encontramos o destino, paramos a busca
        if (visitado.equals(destino)) {
            System.out.println("Caminho encontrado:");
            imprimirCaminho(caminho,origem, destino);
            return caminho;
        }

        // Percorre todos os vizinhos
        for (Aresta<TIPO> aresta : visitado.getArestasSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!marcados.contains(proximo)) {
                marcados.add(proximo);
                fila.add(proximo);
                caminho.put(proximo, aresta); // Registra de onde viemos
            }
        }
    }

    System.out.println("Nenhum caminho encontrado.");
    return null;
}

// Método auxiliar para reconstruir e imprimir o caminho
private void imprimirCaminho(Map<Vertice<TIPO>, Aresta<TIPO>> pai,Vertice<TIPO> origem, Vertice<TIPO> destino) {
    List<Vertice<TIPO>> caminho = new ArrayList<>();
    Vertice<TIPO> atual = destino;

    // Remontando o caminho de trás para frente
    while (atual != null) {
        caminho.add(atual);
        if(atual == origem){
            break;
        }
        atual = pai.get(atual).getInicio();
    }

    // Como reconstruímos ao contrário, invertemos a ordem para imprimir corretamente
    Collections.reverse(caminho);
    for (Vertice<TIPO> v : caminho) {
        System.out.print(v.getDado() + " ");
    }
    System.out.println();
}
}
