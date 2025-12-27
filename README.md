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
###  Questão 17 : Arvore Genealógica

Projeto em Java simples tem como finalidade a criação de arvore Genealógica.



### ✅ Regras de Negócio

#### Validação de Nomes
- **Obrigatoriedade:** não pode ser `null` nem vazio (após `trim`).
- **Formato:** deve conter **apenas letras Unicode e espaços**: regex `^[\\p{L}\\s]+$`.
- **Comprimento:** entre **2 e 100** caracteres.
- **Normalização:** múltiplos espaços internos são reduzidos para um único espaço.

#### Validação de Idade (Geral)
- **Não negativa:** idade não pode ser menor que 0.
- **Limite superior:** não pode ultrapassar `MAX_IDADE_GERAL` (ex.: 125 anos).

#### Idade Mínima dos Pais
- Tanto **mãe** quanto **pai** devem ter uma idade **maior ou igual** a `MIN_IDADE_PAIS` (ex.: 12 anos).

#### Relação Pais–Filho
- **Mãe** e **Pai** devem ser, cada um, **pelo menos `MIN_DIF_PAIS` anos mais velhos** que o **Filho** (ex.: 12 anos).

#### Relação Avós–Pais
- **Avós paternos** devem ser **pelo menos `IDADE_MIN_AVOS` anos mais velhos** que o **Pai**.
- **Avós maternos** devem ser **pelo menos `IDADE_MIN_AVOS` anos mais velhos** que a **Mãe**.
- Idades de avós podem ser **opcionais** (`null`). Se fornecidas, são validadas.

#### Nomes Distintos

- **Pai vs Mãe:** nomes não podem ser iguais (ignora maiúsculas/minúsculas).
- **Pai vs Filho:** nomes não podem ser iguais.
- **Mãe vs Filho:** nomes não podem ser iguais.

###  Questão 18 : Automóvel

Projeto em Java simples tem como finalidade a criação de um sistema de gerenciamento de automóveis.

### ✅ Regras de Negócio
- **Automóvel**
    - Marca não pode ser vazia.
    - Modelo não pode ser vazio.
    - Ano de fabricação deve ser maior que 1885 (ano do primeiro automóvel).
    - Placa deve seguir o formato padrão (ex.: "ABC-1234").
    - Cor não pode ser vazia.
    - Número do chassi deve ter exatamente 17 caracteres.
    - Número de portas deve ser maior que 0.
    - Capacidade do tanque deve ser maior que 0 litros.
    - Quilometragem atual não pode ser negativa.

    

    
    
### 💻 Tecnologia Utilizada
- **Java 25**

### 🧪 Testes Unitários
- **JUnit 5**

### 🔨 Tools
- **IntelliJ IDEA**

### 🌐 Version Control
- **GitHub**
