# API

Criado uma API Java Spring para cadastrar Pautas.
A aplicação tem 3 principais entidades.

-Schedule (Pauta)

-Associate (Associado)

-Vote (Voto)

#

### Implementado testes utilizando `h2`, `junit` e `assertj`.

#

### Utilizado **Postman** para testar a aplicação.
Coleção de endpoints no postman [Aqui](src/main/resources/Pauta.postman_collection.json)

#

### As dependências utilizadas na API são:

`spring-boot-starter:` Conjunto básico de dependências de um aplicativo Spring Boot.

`spring-boot-starter-web:` Suporte para desenvolvimento de aplicativos da web Spring.

`spring-boot-starter-test:` Ferramentas e Bibliotecas para escrever testes unitários/integração.

`spring-boot-starter-validation:` Validações de dados utilizando Anotações.

`spring-boot-starter-data-jdbc:` Acesso a dados usando JDBC (Java Database Connectivity).

`spring-boot-starter-data-jpa:` Acesso a dados usando JPA (Java Persistence API) com Hibernate.

`spring-boot-starter-logging` e `logback-classic:` Bibliotecas de log.

`lombok:` Biblioteca para reduzir a verbosidade do código Java, como geração automática de getters, setters e construtores.

`modelmapper:` Biblioteca para mapeamento de objetos Java de um tipo para outro.

`caelum-stella-core:` Utilitários para validação de documentos, principalmente CPF.

`gson` e `jackson-databind:` Biblioteca para trabalhar com JSON em Java (serialização e desserialização de objetos JSON).

`junit:` Framework para escrever testes unitários em Java.

`assertj-core:` Recursos adicionais para aprimorar os testes unitários.

`h2:` Banco de dados em memória para testes.

`querydsl-apt` e `querydsl-jpa:` Bibliotecas para criação de queries em java.

`httpclient` e `httpcore:` Bibliotecas do Apache HttpClient para fazer requisições HTTP (Rest) em Java.

## Tarefas Bônus

1) Integração com Sistemas Externos
   - Criado uma classe para fazer uma requisição REST para a URL (https://user-info.herokuapp.com/users/{cpf}) porém não foi posível acessar o endpoint.

2) Performance
   - Criado endpoints para salvar listas de entidades para melhorar a performance.

3) Versionamento da API
   - Criado versionamento (v1) para os controllers das entidades.


## Observações

- Para gerar as classes QEntity (entidades utilizadas pela querydsl) é necessário executar o comando `mvn clean install`.
- Caso a varíavel `currentDate` não esteja configurada no Postman, será necessário cadastrá-la na aba 'Pre-request Script' com o valor de `pm.environment.set('currentDate', new Date());`.