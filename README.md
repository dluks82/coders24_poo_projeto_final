# **Coders Bank - Sistema de Gerenciamento Bancário**

## **Descrição do Projeto**

Este é um sistema de gerenciamento bancário desenvolvido em **Java**, que implementa funcionalidades bancárias comuns,
como a criação e gerenciamento de usuários, contas bancárias e transações. O sistema permite realizar operações como
depósitos, saques e verificar saldos, além de manter um histórico de transações para cada conta. Os dados são
armazenados de forma persistente, garantindo que as informações sejam mantidas entre as execuções do sistema.

## **Funcionalidades**

- **Adicionar Usuário:** Insere um novo usuário no sistema.
- **Atualizar Usuário:** Atualiza as informações de um usuário existente.
- **Criar Conta:** Cria uma nova conta bancária para um usuário.
- **Depositar:** Realiza um depósito em uma conta bancária.
- **Sacar:** Realiza um saque de uma conta bancária, verificando a senha e o saldo disponível.
- **Obter Saldo:** Recupera o saldo de uma conta bancária.
- **Histórico de Transações:** Registra e recupera o histórico de transações de uma conta bancária.

## **Requisitos Funcionais**

- **Persistência de Dados:** Os dados de usuários, contas e transações são persistidos para garantir que as informações
  sejam mantidas entre as execuções do sistema.
- **Verificação de Senha:** A senha deve ser verificada para operações sensíveis como saques.
- **Geração de Número de Conta:** Os números de conta são gerados automaticamente com um dígito verificador.

## **Como Executar o Projeto**

Certifique-se de que você tem o **JDK** instalado em sua máquina.

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/dluks82/coders24_poo_projeto_final.git
   ```

2. **Compile o Código-fonte:**
    - Navegue até o diretório onde os arquivos do projeto estão localizados.
    - Compile o código com o seguinte comando:
      ```bash
      javac -d bin src/**/*.java
      ```

3. **Execute o Programa:**
    - Após a compilação, execute o programa utilizando o seguinte comando:
      ```bash
      java -cp bin CodersBankApp
      ```

4. **Interaja com o Sistema:**
    - Utilize a interface de linha de comando para interagir com o sistema e realizar operações bancárias.

## **Estrutura do Projeto**

- **src/repository/BankRepository.java:** Contém a lógica de persistência e manipulação de dados de usuários, contas e
  transações.
- **src/model/User.java:** Define a classe `User` com atributos e métodos relacionados aos usuários.
- **src/model/Account.java:** Define a classe `Account` e suas subclasses `CurrentAccount` e `SavingsAccount`.
- **src/model/TransactionHistory.java:** Define a classe `TransactionHistory` para registrar transações.
- **src/util/DataPersistence.java:** Contém métodos para carregar e salvar dados persistentes.

## **Exceções Personalizadas**

O projeto utiliza exceções personalizadas para tratar erros específicos de forma mais clara e controlada. Por exemplo, a
exceção `InsufficientBalanceException` é lançada quando uma tentativa de saque é feita sem saldo suficiente na conta.
Isso permite que o sistema trate esse tipo de erro de maneira específica, fornecendo feedback adequado ao usuário.

## **Enums**

Enums são utilizados para definir tipos específicos e constantes que melhoram a legibilidade e a segurança do código. No
projeto, o enum `AccountTypeOption` é usado para especificar os tipos de contas bancárias (conta corrente e conta
poupança), enquanto o enum `AccountOption` é usado para definir os tipos de transações (depósito e saque).

## **Estado Gerenciado da Aplicação**

O estado da aplicação é gerenciado por listas que armazenam usuários, contas e históricos de transações. Essas
listas são carregadas a partir de um arquivo de persistência no início da execução e são salvas de volta ao arquivo
sempre que há uma alteração nos dados. Isso garante que o estado da aplicação seja mantido entre as execuções,
proporcionando uma experiência contínua para o usuário.

## **Análise do Projeto**

Este sistema foi desenvolvido com base em conceitos fundamentais de programação orientada a objetos, visando simular um
ambiente bancário real. O projeto oferece um aprendizado prático sobre o gerenciamento de dados, fluxo de controle e
interação com o usuário por meio de um menu simples e funcional.

### Desafios e Oportunidades de Melhoria

#### Desafios:

- **Persistência de Dados:** A implementação da persistência de dados trouxe desafios relacionados à integridade e
  consistência das informações.
- **Colaboração no GitHub:** O processo de colaboração e gerenciamento de contribuições no GitHub foi complexo,
  especialmente para membros menos experientes.

#### Oportunidades de Melhoria:

- **Refatoração para POO Avançada:** O código pode ser refatorado para melhor aderir aos princípios da Orientação a
  Objetos, utilizando padrões de projeto para modularidade e manutenção.
- **Persistência mais Eficiente:** A adoção de bancos de dados relacionais ou não-relacionais poderia melhorar a
  eficiência e escalabilidade do sistema.
- **Interface Gráfica:** A adição de uma interface gráfica (GUI) tornaria o sistema mais acessível e amigável ao
  usuário.
- **Testes Automatizados:** Implementar testes unitários e de integração ajudaria a garantir a confiabilidade do código
  e permitiria expansões futuras com mais segurança.

## **Contribuidores**

- **Diogo Oliveira**
- **Eloise Helena**
- **Irving Lui**
- **Isaque Menezes**

## **Licença**

Este projeto é licenciado sob a [MIT License](LICENSE).
