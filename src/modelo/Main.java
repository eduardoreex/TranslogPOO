package modelo;
import sistema.SistemaTranslog;
import modelo.*;
import java.time.format.DateTimeFormatter;
import javax.xml.transform.Source;
import java.time.LocalDateTime;

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

                    System.out.println("Selecione o Tipo de Cliente: ");
                    System.out.println("1 - Empresarial (10% desconto)");
                    System.out.println("2 - Prioritário (20% desconto)");
                    System.out.print("Opção: ");

                    int tipoCliente = scanner.nextInt();
                    scanner.nextLine();

                    Cliente novoCliente = null;

                    if (tipoCliente == 1) {
                        novoCliente = new ClienteEmpresarial(nomeCli, cnpj, contrato);
                    } else if (tipoCliente == 2) {
                        novoCliente = new ClientePrioritarios(nomeCli,cnpj,contrato);
                    } else {
                    System.out.println("Opção inválida. Criando como genérico.");
                        novoCliente = new Cliente(nomeCli, cnpj, contrato);
                    }
                    sistema.cadastrarCliente(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
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
                    System.out.print("Tipo - leve/média ou pesada: ");
                    String tipoCarga = scanner.nextLine();

                    System.out.print("Peso (kg): ");
                    double pesoCarga = scanner.nextDouble();

                    System.out.print("É perigosa? - true ou false: ");
                    boolean perigosa = scanner.nextBoolean();
                    scanner.nextLine();
                    Carga novaCarga = new Carga(tipoCarga, pesoCarga, perigosa);
                    System.out.print("Distância em KM (use virgula, ex: 100,5): ");

                    double km = scanner.nextDouble();
                    scanner.nextLine();
                    Frete novoFrete = sistema.criarFrete(cliEncontrado, motEncontrado, novaCarga, km);

                    System.out.println("Valor do Frete : R$%.2f%n " + novoFrete.getValorTotal() );

                    System.out.println("\n=== Agendamento ===");
                    System.out.print("Digite a data da entrega (dd/MM/yyyy HH:mm): ");
                    String dataTexto = scanner.nextLine();

                      try {
                        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
                        LocalDateTime dataAgendada = LocalDateTime.parse(dataTexto, formatador);

                        sistema.agendar(novoFrete, dataAgendada);
                        System.out.println(">> Agendamento realizado para: " + dataTexto);
                        break;
                      } catch (Exception e) {
                          System.out.println(" Data inválida! O agendamento falhou. Use o formato: dia/mês/ano hora");
                      }

                    System.out.println("Deseja emitir Nota Fiscal? (s/n)");
                    String respNota = scanner.nextLine();

                    if (respNota.equalsIgnoreCase("s")) {
                        NotaFiscal nf = new NotaFiscal(novoFrete);
                        nf.imprimir();
                    }
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