# Sistema Translog

## 1. Informações Acadêmicas

**Instituição:** Faculdade ICEV  
**Curso:** Engenharia de Software  
**Disciplina:** Programação Orientada a Objetos  
**Professor:** Samuel  

**Integrantes:**  
- Eduardo Oliveira  
- Adriano Batista  

---

## 2. Sobre o Projeto

O **Sistema Translog** é um sistema desenvolvido em Java com o objetivo de simular a gestão de fretes de uma transportadora.  
Ele permite o cadastro de clientes, motoristas, cargas, criação de fretes, agendamento de entregas e emissão de notas fiscais.

Este projeto foi desenvolvido como atividade prática para aplicação dos conceitos de:

- Programação Orientada a Objetos (POO)
- Herança
- Polimorfismo
- Encapsulamento
- Manipulação de datas com `LocalDateTime`
- Tratamento de exceções
- Interação via console
- Organização em pacotes

---

## 3. Funcionalidades do Sistema

O sistema permite:

- Cadastrar clientes (Empresarial, Prioritário ou Genérico)
- Cadastrar motoristas
- Criar fretes associando cliente, motorista e carga
- Calcular valores de frete com base em distância, tipo de carga e risco
- Aplicar descontos automática conforme o tipo de cliente
- Agendar data de entrega
- Calcular multa por atraso
- Emitir nota fiscal
- Listar todos os fretes cadastrados

---

## 4. Estrutura do Projeto

O projeto está organizado nos seguintes pacotes:

- `modelo`: Contém as classes principais do sistema (Cliente, Frete, Carga, Agendamento, etc).
- `sistema`: Contém a classe `SistemaTranslog`, responsável por gerenciar as operações.
- `main`: Contém a classe `Main`, responsável por executar o sistema no console.

Exemplo:
src/
├── modelo/
├── sistema/
└── main/


---

## 5. Como Acessar o Projeto

### 5.1 Pelo GitHub

1. Acesse o repositório:  
  https://github.com/eduardoreex/TranslogPOO.git

2. Clique em **Code** → **Download ZIP**
3. Extraia o arquivo em sua máquina.

---

## 6. Como Rodar o Projeto em sua Máquina

### 6.1 Pré-requisitos

Você precisa ter instalado:

- Java JDK 17 ou superior
- IntelliJ IDEA (ou Eclipse / VS Code com extensão Java)

---

### 6.2 Passo a passo no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Clique em **File → Open**.
3. Selecione a pasta do projeto (onde está a pasta `src`).
4. Aguarde o carregamento do projeto.
5. Vá até o arquivo:

src/main/Main.java



6. Clique com o botão direito na classe `Main`.
7. Clique em **Run 'Main'**.

O sistema será iniciado no terminal do IntelliJ.

---

## 7. Como Usar o Sistema

Quando o programa iniciar, aparecerá um menu como este:

=== Menu Translog ===

Cadastrar Cliente

Cadastrar Motorista

Criar Frete

Listar Fretes

Sair


Basta digitar o número da opção desejada e seguir as instruções mostradas no terminal.

---

## 8. Tecnologias Utilizadas

- Java 17
- IntelliJ IDEA
- Programação Orientada a Objetos
- GitHub

---

## 9. Considerações Finais

O desenvolvimento do Sistema Translog permitiu aplicar na prática os conceitos fundamentais da Programação Orientada a Objetos, promovendo melhor entendimento sobre organização de código, modelagem de classes, encapsulamento e tratamento de erros em Java.

Este projeto é de finalidade **acadêmica**, sem fins comerciais.

---

## 10. Licença

Projeto desenvolvido para fins educacionais no curso de Engenharia de Software da Faculdade ICEV.
