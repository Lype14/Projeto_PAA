public class Aresta<TIPO> {
    private double capacidade;
    private double fluxo;
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    String tipo; // direta ou contrária

    public Aresta(double capacidade, Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        this.capacidade = capacidade;
        this.fluxo = 0;
        this.inicio = inicio;
        this.fim = fim;
    }

    public double getCapacidadeResidual() {
        return capacidade - fluxo;
    }

    public double getCapacidade(){
        return this.capacidade;
    }

    public void adicionarFluxo(double incremento) {
        this.fluxo += incremento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public Vertice<TIPO> getFim() {
        return fim;
    }

    public double getFluxo() {
        return fluxo;
    }

    public Vertice<TIPO> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<TIPO> inicio) {
        this.inicio = inicio;
    }
}
