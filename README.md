# Lista exercicios em Java

## ✅  Orientação a Objetos

### 🎯 Objetivo dos Exercícios

O propósito exercícios tem como finalidade aplicar os conceitos fundamentais da Programação Orientada a Objetos (POO) em 
Java, incluindo:

- **Abstração**
- **Encapsulamento**
- **Herança**
- **Polimorfismo**


As resoluções das questões serão resolvidas com base na lógica de programação e nas boas práticas de implementação em Java, visando o aprendizado e a consolidação dos princípios de POO.

### ✅ Questões Resolvidas


###  Questão 16 : Controle Empréstimo de Livros

Este é um projeto Java simples para gerenciar empréstimos de livros básicos.

### Pré-requisitos

Para compilar e executar este projeto, você precisará ter o **JDK (Java Development Kit) instalado** em sua máquina (versão 8 ou superior é recomendada).

Você pode verificar sua versão abrindo o terminal/prompt de comando e digitando:

``` 
java -version
````

### ✅ Regras de Negócio Testadas

Os testes automatizados garantem que as seguintes regras sejam respeitadas:

- **Pessoa**
    - Nome não pode ser vazio.
    - Idade deve ser maior que 0.
    - Método `validarLeitor()` retorna `true` apenas para leitores válidos.

- **Livro**
    - Título não pode ser vazio.
    - Autor e categoria devem ser informados.

- **Empréstimo**
    - Data de devolução não pode ser anterior à data de empréstimo.
    - Detalhes do empréstimo devem conter:
        - Nome do leitor
        - Título do livro
        - Autor do livro
        - Datas formatadas no padrão brasileiro (`dd/MM/yyyy`)
    - Permite devolução no mesmo dia ou após (dependendo da regra definida).
      ``

### 🧪 Testes Automatizados
Este projeto utiliza **JUnit 5** para validar as regras de negócio.

      
### Version Control
<p>
  
  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/github.png" alt="GitHub" width="22" style="vertical-align: middle;">
  <strong>GitHub</strong>
</p>

### Tools
<p>

  <img src="https://raw.githubusercontent.com/marwin1991/profile-technology-icons/refs/heads/main/icons/intellij.png" alt="IntelliJ" width="22" style="vertical-align: middle;">
  <strong>IntelliJ IDEA</strong>
</p>
