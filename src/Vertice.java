import java.util.ArrayList;

public class Vertice<TIPO> {
    private String nome;
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestasEntrada;
    private ArrayList<Aresta<TIPO>> arestasSaida;


    public Vertice (TIPO valor,String nome){
        this.nome = nome;
        this.dado = valor;
        this.arestasEntrada = new ArrayList<Aresta<TIPO>>();
        this.arestasSaida = new ArrayList<Aresta<TIPO>>();
    }

    public String getNome(){
        return this.nome;
    }
    
    public TIPO getDado(){
        return dado;
    }

    public void setDado(TIPO dado){
        this.dado = dado;
    }
    
    public ArrayList<Aresta<TIPO>> getArestasEntrada() {
        return arestasEntrada;
    }

    public void setArestaEntrada(Aresta<TIPO> arestasEntrada) {
        this.arestasEntrada.add(arestasEntrada);
    }

    public ArrayList<Aresta<TIPO>> getArestasSaida() {
        return arestasSaida;
    }

    public void setArestaSaida(Aresta<TIPO> arestaSaida) {
        this.arestasSaida.add(arestaSaida);
    }

    public void setArestasSaida(ArrayList<Aresta<TIPO>> arestas){
        this.arestasSaida = arestas;
    }
    public void setArestasEntrada(ArrayList<Aresta<TIPO>> arestas){
        this.arestasEntrada = arestas;
    }

    public Aresta<TIPO> getArestaSaida(Vertice<TIPO> inicial, Vertice<TIPO> fim){
        for(Aresta<TIPO> aresta : arestasSaida){
            if(aresta.getInicio().equals(inicial) && aresta.getFim().equals(fim)){
                return aresta;
            }
        }
        return null;
    }

    public Aresta<TIPO> getArestaEntrada(Vertice<TIPO> inicial, Vertice<TIPO> fim){
        for(Aresta<TIPO> aresta : arestasEntrada){
            if(aresta.getInicio().equals(inicial) && aresta.getFim().equals(fim)){
                return aresta;
            }
        }
        return null;
    }

    public void adicionarArestaEntrada(Aresta<TIPO> aresta){
        this.arestasEntrada.add(aresta);

    }

    public void adicionarArestaSaida(Aresta<TIPO> aresta){
        this.arestasSaida.add(aresta);
    }

}
