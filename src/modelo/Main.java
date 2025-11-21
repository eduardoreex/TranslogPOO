package modelo;

import sistema.SistemaTranslog;

import java.time.format.DateTimeFormatter;
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

            int opcao = lerInteiro(scanner, "Escolha uma opção: ", 0, 4);

            switch (opcao) {

                case 0:
                    System.out.println("\n=== RELATÓRIO FINAL ===");

                    if (sistema.getFretes().isEmpty()) {
                        System.out.println("Nenhum frete foi registrado.");
                    } else {
                        double totalGeral = 0;

                        for (Frete f : sistema.getFretes()) {
                            System.out.println(
                                    "Cliente: " + f.getCliente().getNome() +
                                            " | Motorista: " + f.getMotorista().getNome() +
                                            " | Valor: R$ " + String.format("%.2f", f.getValorTotal())
                            );

                            totalGeral += f.getValorTotal();
                        }

                        System.out.println("\nTotal de fretes: " + sistema.getFretes().size());
                        System.out.printf("Valor total movimentado: R$ %.2f%n", totalGeral);
                    }

                    System.out.println("\nSistema finalizado.");
                    executando = false;
                    break;

                case 1:
                    System.out.println("\n==== Novo Cliente ===");

                    System.out.print("Nome: ");
                    String nomeCli = scanner.nextLine();

                    System.out.print("CNPJ: ");
                    String cnpj = scanner.nextLine();

                    System.out.print("Contrato: ");
                    String contrato = scanner.nextLine();

                    System.out.println("Tipo de Cliente:");
                    System.out.println("1 - Empresarial (10%)");
                    System.out.println("2 - Prioritário (20%)");

                    int tipoCliente = lerInteiro(scanner, "Opção: ", 1, 2);

                    Cliente novoCliente;

                    if (tipoCliente == 1) {
                        novoCliente = new ClienteEmpresarial(nomeCli, cnpj, contrato);
                    } else {
                        novoCliente = new ClientePrioritarios(nomeCli, cnpj, contrato);
                    }

                    sistema.cadastrarCliente(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n=== Novo Motorista ===");

                    System.out.print("Nome do Motorista: ");
                    String nomeMot = scanner.nextLine();

                    System.out.print("CNH: ");
                    String cnh = scanner.nextLine();

                    Motorista novoMotorista = new Motorista(nomeMot, cnh);
                    sistema.cadastrarMotorista(novoMotorista);

                    System.out.println("Motorista cadastrado!");
                    break;

                case 3:
                    System.out.println("\n=== Criando Frete ===");

                    System.out.print("Nome do Cliente: ");
                    String buscaCli = scanner.nextLine();
                    Cliente cliEncontrado = sistema.buscarCliente(buscaCli);

                    if (cliEncontrado == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    System.out.print("Nome do Motorista: ");
                    String buscaMot = scanner.nextLine();
                    Motorista motEncontrado = sistema.buscarMotorista(buscaMot);

                    if (motEncontrado == null) {
                        System.out.println("Motorista não encontrado.");
                        break;
                    }

                    System.out.print("Tipo da carga (leve/média/pesada): ");
                    String tipoCarga = scanner.nextLine();

                    double pesoCarga = lerDouble(scanner, "Peso (kg): ");

                    boolean perigosa = lerBoolean(scanner, "Carga perigosa? (true/false ou sim/não): ");

                    Carga novaCarga = new Carga(tipoCarga, pesoCarga, perigosa);

                    double km = lerDouble(scanner, "Distância (km): ");

                    Frete novoFrete = sistema.criarFrete(
                            cliEncontrado,
                            motEncontrado,
                            novaCarga,
                            km
                    );

                    System.out.printf("Valor do frete: R$ %.2f%n", novoFrete.getValorTotal());

                    LocalDateTime dataAgendada = lerData(scanner);
                    sistema.agendar(novoFrete, dataAgendada);

                    System.out.println("Frete agendado com sucesso!");

                    System.out.print("Deseja emitir Nota Fiscal? (s/n): ");
                    String respNota = scanner.nextLine();

                    if (respNota.equalsIgnoreCase("s")) {
                        NotaFiscal nf = new NotaFiscal(novoFrete);
                        nf.imprimir();
                    }

                    break;

                case 4:
                    System.out.println("\n=== Lista de Fretes ===");

                    if (sistema.getFretes().isEmpty()) {
                        System.out.println("Nenhum frete cadastrado.");
                    } else {
                        for (Frete f : sistema.getFretes()) {
                            System.out.println("Cliente: " + f.getCliente().getNome()
                                    + " | Valor: R$ " + String.format("%.2f", f.getValorTotal()));
                        }
                    }
                    break;
            }
        }

        scanner.close();
    }

    // ================= FUNÇÕES DE PROTEÇÃO =================

    public static int lerInteiro(Scanner scanner, String mensagem, int min, int max) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());

                if (valor >= min && valor <= max) {
                    return valor;
                } else {
                    System.out.println("Erro: digite um número entre " + min + " e " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite apenas números inteiros.");
            }
        }
    }

    public static double lerDouble(Scanner scanner, String mensagem) {
        double valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite um número válido!");
            }
        }
    }

    public static boolean lerBoolean(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().toLowerCase();

            if (entrada.equals("true") || entrada.equals("t") || entrada.equals("sim") || entrada.equals("s")) {
                return true;
            }

            if (entrada.equals("false") || entrada.equals("f") || entrada.equals("nao")
                    || entrada.equals("não") || entrada.equals("n")) {
                return false;
            }

            System.out.println("Erro: digite true/false ou sim/não.");
        }
    }

    public static LocalDateTime lerData(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Digite a data (dd/MM/yyyy HH:mm): ");
                String dataTexto = scanner.nextLine();

                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                return LocalDateTime.parse(dataTexto, formatador);

            } catch (Exception e) {
                System.out.println("Data inválida! Formato correto: dd/MM/yyyy HH:mm");
            }
        }
    }
}