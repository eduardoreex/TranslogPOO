package modelo;

public class Cliente {
    private String nome;
    private String cnpj;
    private String contrato;

    public Cliente (String nome, String cnpj, String contrato) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.contrato = contrato;
    }

    public double calcularDesconto() {
        return 0.0;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }
    public String getContrato() {
        return contrato;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
}
