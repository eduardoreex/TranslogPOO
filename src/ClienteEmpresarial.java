public class ClienteEmpresarial extends Cliente {

    public ClienteEmpresarial (String nome, String cnpj, String contrato) {
        super(nome, cnpj, contrato);
    }
    @Override
    public double calcularDesconto() {
        return 0.10;
    }
}
