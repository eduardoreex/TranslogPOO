package modelo;

public class Motorista {
    private String nome;
    private String cnh;
    private boolean disponivel;
    public Motorista(String nome, String cnh) {
        this.nome = nome;
        this.cnh = cnh;
        this.disponivel  = true;

    }
    public String getNome() {
        return nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
