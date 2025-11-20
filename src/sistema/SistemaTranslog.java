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

    private final String DIR = "dados/";

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
                // ClienteEmpresarial recebe : nome ; cnpj ; contrato
                bw.write(c.getNome() + ";" + c.getCnpj() + ";" + c.getContrato());
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

                // salvar apenas a data agendada
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
                // Salvando apenas o nome do cliente
                bw.write(f.getCliente().getNome());
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar fretes!");
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

                // Agora deve ter 3 campos: nome ; cnpj ; contrato
                clientes.add(new ClienteEmpresarial(p[0], p[1], p[2]));
            }

        } catch (Exception e) {
            System.out.println("Erro ao carregar clientes!");
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

        // Não é possível reconstruir um Frete apenas com "nome do cliente".
        // É necessário Cliente + Motorista + Carga + Distância
    }

    private void carregarAgendamentos() {
        File arq = new File(DIR + "agendamentos.txt");
        if (!arq.exists()) return;

        // Também não é possível reconstruir Agendamento
        // porque precisamos do Frete
    }

    public ArrayList<Cliente> getClientes() { return clientes; }
    public ArrayList<Motorista> getMotoristas() { return motoristas; }
    public ArrayList<Agendamento> getAgendamentos() { return agendamentos; }
    public ArrayList<Frete> getFretes() { return fretes; }}