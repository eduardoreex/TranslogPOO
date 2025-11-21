package modelo;
import sistema.SistemaTranslog;
import modelo.*;

import javax.xml.transform.Source;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SistemaTranslog sistema = new SistemaTranslog();

        boolean executando = true;

        while (executando) {

            System.out.println("\n=== Menu Translog ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Motorista");
            System.out.println("3. Criar Frete");
            System.out.println("4. Listar Fretes ");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println();
                    System.out.println("\n==== Novo Cliente ===");
                    System.out.print("Nome: ");
                    String nomeCli = scanner.nextLine();

                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();

                    System.out.println("Contrato: ");
                    String contrato = scanner.nextLine();

                    Cliente novoCliente = new ClienteEmpresarial(nomeCli, cnpj, contrato);
                    sistema.cadastrarCliente(novoCliente);
                    System.out.println("Cliente salvo!");

                    break;
                case 2:
                    System.out.println("\n=== Novo do Motorista ===");
                    System.out.println("Nome do Motorista: ");
                    String nomeMot = scanner.nextLine();

                    System.out.println("CNH: ");
                    String cnh = scanner.nextLine();

                    Motorista novoMotorista = new Motorista(nomeMot, cnh);

                    sistema.cadastrarMotorista(novoMotorista);
                    break;


                case 3:
                    System.out.println("\n=== Criando Frete ===");
                    System.out.println("Digite o nome do Cliente: ");
                    String buscaCli = scanner.nextLine();

                    Cliente cliEncontrado = sistema.buscarCliente(buscaCli);

                    if (cliEncontrado == null) {
                        System.out.println(" Erro : cliente não encontrado");
                        break;
                    }

                    System.out.print("Digite o nome do Motorista: ");
                    String buscaMot = scanner.nextLine();
                    Motorista motEncontrado = sistema.buscarMotorista(buscaMot);

                    if (motEncontrado == null) {
                        System.out.println(" Erro : Motorista não encontrado");
                        break;
                    }
                    System.out.println("Dados da Carga:");
                    System.out.print("Descrição: ");
                    String descCarga = scanner.nextLine();

                    System.out.print("Tipo - leve/média ou pesada: ");
                    String tipoCarga = scanner.nextLine();

                    System.out.print("É perigosa? - true ou false: ");
                    boolean perigosa = scanner.nextBoolean();
                    scanner.nextLine();

                    Carga novaCarga = new Carga(descCarga, tipoCarga, perigosa);

                    System.out.print("Distância em KM (use virgula, ex: 100,5): ");

                    double km = scanner.nextDouble();
                    scanner.nextLine();
                    Frete novoFrete = sistema.criarFrete(cliEncontrado, motEncontrado, novaCarga, km);

                    System.out.println("Valor do Frete : R$ " + novoFrete.getValorTotal() );
                    break;

                case 4:
                    System.out.println("\n=== Lista de Fretes ===");

                    for (Frete f : sistema.getFretes()) {
                        System.out.println("Cliente: " +  f.getCliente().getNome() + " Valor: R$ " + f.getValorTotal());
                    }
                    break;

            }
        }
    }
}