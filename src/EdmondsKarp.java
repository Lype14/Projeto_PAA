import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EdmondsKarp{

    public <TIPO> Grafo<TIPO> edmondsKarp(Grafo<TIPO> redePrincipal, Vertice<TIPO> S, Vertice<TIPO> T){
        for(Aresta<TIPO> e : redePrincipal.getArestas()){// zerando o fluxo da rede
            e.adicionarFluxo(- e.getFluxo());
        }
        Grafo<TIPO> redeResidual = montarRedeResidual(redePrincipal);
        for(Aresta aresta: redeResidual.getArestas()){
            System.out.println( aresta.getInicio().getDado() + " " + aresta.getFim().getDado() + " " + aresta.getCapacidade());
        }
        Map<Vertice<TIPO>, Aresta<TIPO>> caminho = new HashMap<>();
        boolean caminhoEncontrado = redeResidual.buscaCaminhoAumentante(S,T,caminho);

        while(caminhoEncontrado == true){
            atualizarFluxo(redePrincipal, caminho, S, T);
            redeResidual = montarRedeResidual(redePrincipal);
            caminhoEncontrado = redeResidual.buscaCaminhoAumentante(S,T,caminho);
        }
        return redePrincipal;
    }




    public <TIPO> Grafo<TIPO> montarRedeResidual(Grafo<TIPO> grafoPrincipal) {
        Grafo<TIPO> redeResidual = new Grafo<>(); // Criando a rede residual
        
        ArrayList<Vertice<TIPO>> vertices = grafoPrincipal.getVertices(); // Os vértices na rede residual são os mesmos
        for(Vertice vertice : vertices){
            vertice.setArestasEntrada(new ArrayList<>());
            vertice.setArestasSaida(new ArrayList<>());
        }
        ArrayList<Aresta<TIPO>> arestas = grafoPrincipal.getArestas(); // Pegando as arestas do grafo principal
        ArrayList<Aresta<TIPO>> arestasRedeResidual = new ArrayList<>(); // Lista de arestas da rede residual
        
        redeResidual.setVertices(vertices); // Adicionando os vértices à rede residual
    
        // Percorrendo as arestas do grafo principal para criar a rede residual
        for (Aresta<TIPO> aresta : arestas) {
            double capacidadeResidual = aresta.getCapacidadeResidual();
    
            // Se a capacidade residual for maior que zero, adicionamos a aresta na rede residual
            if (capacidadeResidual > 0) {
                Aresta<TIPO> novaArestaDireta = new Aresta<>(capacidadeResidual, aresta.getInicio(), aresta.getFim());
                novaArestaDireta.setTipo("direta");
                aresta.getInicio().setArestaSaida(novaArestaDireta);
                aresta.getFim().setArestaEntrada(novaArestaDireta);
                arestasRedeResidual.add(novaArestaDireta);
                for(Vertice<TIPO> vertice : vertices){
                    if(novaArestaDireta.getInicio().getNome().equals(vertice.getNome())){
                        vertice.setArestaSaida(aresta);
                        break;
                    }
                    else if(novaArestaDireta.getFim().getNome().equals(vertice.getNome())){
                        vertice.setArestaEntrada(aresta);
                        break;
                    }
                }
            }
    
            // Criando a aresta reversa (contrária) com o fluxo atual como capacidade
            if (aresta.getFluxo() > 0) {
                Aresta<TIPO> novaArestaContraria = new Aresta<>(aresta.getFluxo(), aresta.getFim(), aresta.getInicio());
                novaArestaContraria.setTipo("contraria");
                aresta.getInicio().setArestaEntrada(novaArestaContraria);
                aresta.getFim().setArestaSaida(novaArestaContraria);
                arestasRedeResidual.add(novaArestaContraria);
                for(Vertice<TIPO> vertice : vertices){
                    if(novaArestaContraria.getInicio().getNome().equals(vertice.getNome())){
                        vertice.setArestaSaida(aresta);
                        break;
                    }
                    else if(novaArestaContraria.getFim().getNome().equals(vertice.getNome())){
                        vertice.setArestaEntrada(aresta);
                        break;
                    }
                }
            }
            
        }
    
        // Adicionamos as arestas à rede residual
        redeResidual.setArestas(arestasRedeResidual);
    
        return redeResidual;
    }



    public <TIPO> Grafo<TIPO> atualizarFluxo(Grafo<TIPO> redePrincipal,Map<Vertice<TIPO>, Aresta<TIPO>> caminho, Vertice<TIPO> S, Vertice<TIPO> T){
        if (!caminho.containsKey(T)) {
            System.out.println("Não é possível atualizar o fluxo");
            return redePrincipal; // Se não há caminho aumentante, retorna a rede sem alterações
        }
    
        // Determinar o fluxo mínimo no caminho aumentante
        Double fluxoMinimo = Double.MAX_VALUE;
        Vertice<TIPO> atual = T;
        while (!atual.equals(S)) {
            Aresta<TIPO> aresta = caminho.get(atual);
            fluxoMinimo = Math.min(fluxoMinimo, aresta.getCapacidade());
            atual = aresta.getInicio();
        }
    
        // Atualizar os fluxos ao longo do caminho aumentante
        atual = T;
        while (!atual.equals(S)) {
            Aresta<TIPO> aresta = caminho.get(atual);
            // Ajustar a aresta residual
            if (aresta.tipo.equals("contraria")) {
                redePrincipal.getAresta(aresta.getFim(), aresta.getFim()).adicionarFluxo(-fluxoMinimo);;
                System.out.println(redePrincipal.getAresta(aresta.getFim(), aresta.getFim()).getFluxo());
            } else {
                redePrincipal.getAresta(aresta.getInicio(), aresta.getFim()).adicionarFluxo(fluxoMinimo);
                System.out.println(redePrincipal.getAresta(aresta.getInicio(), aresta.getFim()).getFluxo());
            }
            atual = aresta.getInicio();
        }
    
        return redePrincipal;
    }
}