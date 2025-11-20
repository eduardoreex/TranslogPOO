package modelo;

public class ClientePrioritarios extends Cliente {

    public ClientePrioritarios(String nome, String cnpj, String contrato) {
        super(nome, cnpj, contrato);
    }

    @Override
    public double calcularDesconto() {
        return 0.20;
    }
}
