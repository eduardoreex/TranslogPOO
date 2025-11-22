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

- `modelo`: Contém as classes principais do sistema (Main, Cliente, Frete, Carga, Agendamento, etc).
- `sistema`: Contém a classe `SistemaTranslog`, responsável por gerenciar as operações.


Exemplo:
src/
├── modelo/
├── sistema/



---

## 5. Como Acessar o Projeto

### 5.1 Pelo GitHub

1. Acesse o repositório:  
  https://github.com/eduardoreex/TranslogPOO.git

2. Clique em **Code** → **Download ZIP**
3. Extraia o arquivo em sua máquina.

### 5.2 Pelo GitHub (Usando git clone)

Se você já tem o Git instalado, pode clonar o projeto pelo terminal:

git clone https://github.com/eduardoreex/TranslogPOO.git


Depois disso:

Abra o IntelliJ IDEA

Clique em File → Open

Selecione a pasta do projeto clonado (TranslogPOO)

Aguarde o carregamento completo do projeto

--- 
### 5.3. Sistema de Salvamento no Google Drive

O projeto utiliza uma pasta sincronizada do Google Drive, sem uso de API.

Os dados são salvos automaticamente em arquivos .txt dentro da pasta:

G:/Meu Drive/TranslogDados/

Arquivos utilizados:

clientes.txt

motoristas.txt

fretes.txt

agendamentos.txt

Esses arquivos são sincronizados automaticamente pelo Google Drive, permitindo acesso aos dados em qualquer computador conectado à mesma conta.

Não é utilizada nenhuma API do Google, apenas a sincronização local do Drive.

## 6. Como Rodar o Projeto em sua Máquina

### 6.1 Pré-requisitos

Você precisa ter instalado:

- Java JDK 17 ou superior
- IntelliJ IDEA (ou Eclipse / VS Code com extensão Java)
- Google Drive 

6.2 Configurando o Google Drive

Instale o Google Drive no seu computador.

Crie a pasta:

G:/Meu Drive/TranslogDados/


Dentro dela, crie os arquivos:

clientes.txt
motoristas.txt
fretes.txt
agendamentos.txt


Se os arquivos não existirem, o próprio sistema tentará criá-los automaticamente.

6.3 Executando no IntelliJ

Abra o IntelliJ IDEA

Clique em File → Open

Selecione a pasta do projeto

Aguarde o carregamento completo

Abra o arquivo:

src/main/Main.java


Clique com o botão direito → Run 'Main'

O sistema será iniciado no terminal do IntelliJ.

---

## 7.. Como Usar o Sistema

Ao iniciar, será apresentado um menu semelhante a este:

===== Sistema Translog =====
1 - Cadastrar Cliente
2 - Cadastrar Motorista
3 - Criar Frete
4 - Listar Fretes
5 - Gerar Relatório
6 - Sair


Digite o número da opção desejada e siga as instruções exibidas no terminal.

Ao escolher a opção Sair, o sistema:

Salva todos os dados
Gera um relatório final
Encerra corretamente o programa

---

### 8 Relatório Final

Ao encerrar o sistema, é gerado um relatório contendo:

Tempo total de execução

Número de operações realizadas

Quantidade de fretes criados

Quantidade de cadastros realizados

Tarefas executadas por status

Resumo geral do uso do sistema

Esse relatório pode futuramente ser adaptado para exportação em PDF.

## 9. Tecnologias Utilizadas

- Java 17
- IntelliJ IDEA
- Programação Orientada a Objetos
- GitHub

---

## 10. Considerações Finais
O Sistema Translog foi desenvolvido com fins acadêmicos, permitindo a aplicação prática dos conceitos de Programação Orientada a Objetos, organização de sistemas, persistência de dados e controle de fluxo em aplicações Java.

Este projeto possui finalidade exclusivamente educacional e não possui fins comerciais.


---

## 11. Licença

Projeto desenvolvido para fins educacionais no curso de Engenharia de Software da Faculdade ICEV.
