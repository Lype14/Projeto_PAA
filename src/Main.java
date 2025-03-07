import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Grafo redePrincipal = new Grafo<>();
        
        EdmondsKarp edmondsKarp = new EdmondsKarp();
        // Criando arestas com capacidades
        Vertice<String> S = new Vertice<String>("Recife","Recife");
        Vertice<String> A = new Vertice<String>("João Pessoa","João Pessoa");
        Vertice<String> B = new Vertice<String>("Maceió", "Maceió");
        Vertice<String> C = new Vertice<String>("Fortaleza","Fortaleza");
        Vertice<String> D = new Vertice<String>("Salvador","Salvador");
        Vertice<String> T = new Vertice<String>("Xique Xique","Xique Xique");

        Aresta aresta1 = new Aresta(4.0,S,A);
        Aresta aresta2 = new Aresta(10.0,S,B);
        Aresta aresta3 = new Aresta(4.0,A,C);
        Aresta aresta4 = new Aresta(13.0,B,C);
        Aresta aresta5 = new Aresta(10.0,C,T);
        Aresta aresta6 = new Aresta(4.0,D,T);
        Aresta aresta7 = new Aresta(4.0,B,D);

        S.setArestaSaida(aresta1);
        S.setArestaSaida(aresta2);
        A.setArestaEntrada(aresta1);
        A.setArestaSaida(aresta3);
        B.setArestaEntrada(aresta2);
        B.setArestaSaida(aresta4);
        B.setArestaSaida(aresta7);
        C.setArestaEntrada(aresta3);
        C.setArestaEntrada(aresta4);
        C.setArestaSaida(aresta5);
        T.setArestaEntrada(aresta5);
        T.setArestaEntrada(aresta6);
        D.setArestaEntrada(aresta7);
        D.setArestaSaida(aresta6);
        

        ArrayList<Aresta> arestas = new ArrayList<>();
        arestas.add(aresta1);
        arestas.add(aresta2);
        arestas.add(aresta3);
        arestas.add(aresta4);
        arestas.add(aresta5);
        arestas.add(aresta6);
        arestas.add(aresta7);

         // Criando vértices
        redePrincipal.adicionarVertice(S);
        redePrincipal.adicionarVertice(A);
        redePrincipal.adicionarVertice(B);
        redePrincipal.adicionarVertice(C);
        redePrincipal.adicionarVertice(D);
        redePrincipal.adicionarVertice(T);

        redePrincipal.setArestas(arestas);
        
        Grafo fluxoMaximo = edmondsKarp.edmondsKarp(redePrincipal,S, T);
        ArrayList<Aresta> arestasFluxoMaximo;
        arestasFluxoMaximo = fluxoMaximo.getArestas();
        for(Aresta aresta: arestasFluxoMaximo){
            System.out.println( aresta.getInicio().getDado() + " " + aresta.getFim().getDado() + " " + aresta.getFluxo());
        }

        // Testando a busca em largura
        System.out.println("Busca em largura a partir do vértice 1:");
    

        // Testando a busca por caminho aumentante na rede residual
        //Map<Vertice<Integer>, Aresta<Integer>> caminho = new HashMap<>();
       // boolean caminhoEncontrado = grafo.buscaCaminhoAumentante(1, 4, caminho);

       // if (caminhoEncontrado) {
         //   System.out.println("Caminho aumentante encontrado!");
         //   Vertice<Integer> destino = grafo.getVertice(3);
         //   while (caminho.containsKey(destino)) {
         //       Aresta<Integer> aresta = caminho.get(destino);
           //     System.out.println("De " + aresta.getInicio().getDado() + " para " + destino.getDado());
           //     destino = aresta.getInicio();
           // }
     //   } else {
      //      System.out.println("Nenhum caminho aumentante encontrado.");
      //  }
   // }
}
}
