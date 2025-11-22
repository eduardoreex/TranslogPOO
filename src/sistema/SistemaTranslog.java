package sistema;

import modelo.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SistemaTranslog {

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Motorista> motoristas = new ArrayList<>();
    private ArrayList<Agendamento> agendamentos = new ArrayList<>();
    private ArrayList<Frete> fretes = new ArrayList<>();
    private final String DIR = "G:/Meu Drive/TranslogDados/";

    public SistemaTranslog() {
        new File(DIR).mkdir();
        carregarDados();
    }

    public void cadastrarCliente(Cliente c) {
        clientes.add(c);
        salvarClientes();
    }

    public void cadastrarMotorista(Motorista m) {
        motoristas.add(m);
        salvarMotoristas();
    }

    public Agendamento agendar(Frete frete, LocalDateTime dataHora) {
        Agendamento novo = new Agendamento(frete, dataHora);
        agendamentos.add(novo);
        salvarAgendamentos();
        return novo;
    }

    public Frete criarFrete(Cliente cli, Motorista mot, Carga carga, double km) {
        Frete f = new Frete(cli, carga, mot, km);
        fretes.add(f);
        salvarFretes();
        return f;
    }

    public void concluirEntrega(Agendamento ag) {
        ag.confirmarEntrega(LocalDateTime.now());
        salvarAgendamentos();
    }

    private void salvarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DIR + "clientes.txt"))) {

            for (Cliente c : clientes) {
                String tipo = (c instanceof ClientePrioritarios) ? "2" : "1";

                bw.write(tipo + ";"+ c.getNome() + ";" + c.getCnpj() + ";" + c.getContrato());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar clientes!");
        }
    }

    private void salvarMotoristas() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DIR + "motoristas.txt"))) {

            for (Motorista m : motoristas) {
                bw.write(m.getNome() + ";" + m.getCnh());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar motoristas!");
        }
    }

    private void salvarAgendamentos() {
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(DIR + "agendamentos.txt"))) {

            for (Agendamento a : agendamentos) {

                bw.write(a.getDataAgendada().toString());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar agendamentos!");
        }
    }

    private void salvarFretes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DIR + "fretes.txt"))) {
            for (Frete f : fretes) {
                bw.write(f.getCliente().getNome() + ";" +
                        f.getMotorista().getNome() + ";" +
                        f.getDistanciaKm() + ";" +
                        f.getCarga().getTipo() + ";" +
                        f.getCarga().isPerigosaOuFragil());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar fretes!" + e.getMessage());
        }
    }


    private void carregarDados() {
        carregarClientes();
        carregarMotoristas();
        carregarFretes();
        carregarAgendamentos();
    }

    private void carregarClientes() {
        File arq = new File(DIR + "clientes.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");

                if (p.length >= 4) {
                    String tipo = p[0];
                    String nome = p[1];
                    String cnpj = p[2];
                    String contrato = p[3];

                    Cliente c;
                    if (tipo.equals("2")) {
                        c = new ClientePrioritarios(nome, cnpj, contrato);
                    } else {
                        c = new ClienteEmpresarial(nome, cnpj, contrato);
                    }
                    clientes.add(c);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes!");
        }
    }

    public void carregarClientesDeArquivo(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                String[] partes = linha.split(";");

                if (partes.length == 3) {
                    Cliente cliente = new Cliente(
                            partes[0], // nome
                            partes[1], // cpf
                            partes[2]  // contato
                    );

                    clientes.add(cliente);
                }
            }

            System.out.println("Clientes carregados com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
    }

    private void carregarMotoristas() {
        File arq = new File(DIR + "motoristas.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                motoristas.add(new Motorista(p[0], p[1]));
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar motoristas!");
        }
    }

    private void carregarFretes() {
        File arq = new File(DIR + "fretes.txt");
        if (!arq.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                Cliente cli = buscarCliente(p[0]);
                Motorista mot = buscarMotorista(p[1]);

                if (cli != null && mot != null) {
                    double km = Double.parseDouble(p[2]);
                    String tipoCarga = p[3];
                    boolean perigosa = Boolean.parseBoolean(p[4]);

                    Carga cargaRecuperada = new Carga(tipoCarga, 0.0, perigosa);

                    Frete f = new Frete(cli, cargaRecuperada, mot, km);
                    fretes.add(f);
                }
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar motoristas!");
        }
    }

    private void carregarAgendamentos() {
        File arq = new File(DIR + "agendamentos.txt");
        if (!arq.exists()) return;

    }

    public Cliente buscarCliente(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return c;
            }
        }
        return null;
    }
    public Motorista buscarMotorista(String nome) {
        for (Motorista m : motoristas) {
            if (m.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return m;
            }
        }
        return null;
    }
    public ArrayList<Cliente> getClientes () {
                return clientes;
            }
    public ArrayList<Motorista> getMotoristas () {
                return motoristas;
            }
    public ArrayList<Agendamento> getAgendamentos () {
                return agendamentos;
            }
    public ArrayList<Frete> getFretes () {
                return fretes;
            }
        }
