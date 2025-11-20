public class Frete {
    private Cliente cliente;
    private Carga carga;
    private Motorista motorista;
    private double distanciaKm;
    private double valorTotal;

    public Frete (Cliente cliente, Carga carga, Motorista motorista, double distanciaKm) {
        this.carga = carga;
        this.cliente = cliente;
        this.distanciaKm = distanciaKm;
        this.motorista = motorista;
    }
    public double calcularFrete() {
        double precoPorkm =  this.carga.getValorBaseKm();
        double custoInicial = this.distanciaKm * precoPorkm;

        if (this.carga.isPerigosaOuFragil()) {
            custoInicial = custoInicial + (custoInicial * 0.40);
        }

        double desconto = this.cliente.calcularDesconto();
        double valorFinal = custoInicial - (custoInicial * desconto);

        this.valorTotal = valorFinal;

        return valorFinal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
