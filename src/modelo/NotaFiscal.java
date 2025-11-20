package modelo;

import java.time.LocalDate;

public class NotaFiscal {

    private Frete frete;
    private LocalDate emissao;

    public NotaFiscal(Frete frete) {
        this.frete = frete;
        this.emissao = LocalDate.now();
    }

    public void imprimir() {
        System.out.println("=== NOTA FISCAL ===");
        System.out.println("modelo.Cliente: " + frete.getCliente().getNome());
        System.out.println("CNPJ: " + frete.getCliente().getCnpj());
        System.out.println("Valor Total: R$ " + frete.getValorTotal());
        System.out.println("Emitida em: " + emissao);
        System.out.println("====================");
    }
}