public class Aresta<TIPO> {
    private double capacidade;
    private double fluxo;
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    private Aresta<TIPO> reversa;

    public Aresta(double capacidade, Vertice<TIPO> inicio, Vertice<TIPO> fim) {
        this.capacidade = capacidade;
        this.fluxo = 0;
        this.inicio = inicio;
        this.fim = fim;
        this.reversa = null;
    }

    public double getCapacidadeResidual() {
        return capacidade - fluxo;
    }

    public void adicionarFluxo(double incremento) {
        this.fluxo += incremento;
        this.reversa.fluxo -= incremento; // Ajusta a reversa
    }

    public void setReversa(Aresta<TIPO> reversa) {
        this.reversa = reversa;
    }

    public Aresta<TIPO> getReversa() {
        return reversa;
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
