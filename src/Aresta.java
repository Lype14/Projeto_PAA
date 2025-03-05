public class Aresta<TIPO> {
    private Double capacidade;
    private Double fluxo;
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    String tipo; // direta ou contraria

    public Aresta(Double capacidade, Double fluxo, Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        this.capacidade = capacidade;
        this.inicio = inicio;
        this.fim = fim;
        this.fluxo = fluxo;
    }

    public Double getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Double capacidade) {
        this.capacidade = capacidade;
    }

    public Double getFluxo() {
        return fluxo;
    }

    public void setFluxo(Double fluxo) {
        this.fluxo = fluxo;
    }

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public Vertice<TIPO> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<TIPO> inicio) {
        this.inicio = inicio;
    }

    public Vertice<TIPO> getFim() {
        return fim;
    }

    public void setFim(Vertice<TIPO> fim) {
        this.fim = fim;
    }

}
