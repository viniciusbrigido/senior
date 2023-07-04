# API

Criado uma API Java Spring para cadastrar Pautas.
A aplica��o tem 3 principais entidades.

-Schedule (Pauta)

-Associate (Associado)

-Vote (Voto)

#

### Implementado testes utilizando `h2`, `junit` e `assertj`.

#

### Utilizado **Postman** para testar a aplica��o.
Cole��o de endpoints no postman [Aqui](src/main/resources/Pauta.postman_collection.json)

#

### As depend�ncias utilizadas na API s�o:

`spring-boot-starter:` Conjunto b�sico de depend�ncias de um aplicativo Spring Boot.

`spring-boot-starter-web:` Suporte para desenvolvimento de aplicativos da web Spring.

`spring-boot-starter-test:` Ferramentas e Bibliotecas para escrever testes unit�rios/integra��o.

`spring-boot-starter-validation:` Valida��es de dados utilizando Anota��es.

`spring-boot-starter-data-jdbc:` Acesso a dados usando JDBC (Java Database Connectivity).

`spring-boot-starter-data-jpa:` Acesso a dados usando JPA (Java Persistence API) com Hibernate.

`spring-boot-starter-logging` e `logback-classic:` Bibliotecas de log.

`lombok:` Biblioteca para reduzir a verbosidade do c�digo Java, como gera��o autom�tica de getters, setters e construtores.

`modelmapper:` Biblioteca para mapeamento de objetos Java de um tipo para outro.

`caelum-stella-core:` Utilit�rios para valida��o de documentos, principalmente CPF.

`gson` e `jackson-databind:` Biblioteca para trabalhar com JSON em Java (serializa��o e desserializa��o de objetos JSON).

`junit:` Framework para escrever testes unit�rios em Java.

`assertj-core:` Recursos adicionais para aprimorar os testes unit�rios.

`h2:` Banco de dados em mem�ria para testes.

`querydsl-apt` e `querydsl-jpa:` Bibliotecas para cria��o de queries em java.

`httpclient` e `httpcore:` Bibliotecas do Apache HttpClient para fazer requisi��es HTTP (Rest) em Java.

## Tarefas B�nus

1) Integra��o com Sistemas Externos
   - Criado uma classe para fazer uma requisi��o REST para a URL (https://user-info.herokuapp.com/users/{cpf}) por�m n�o foi pos�vel acessar o endpoint.

2) Performance
   - Criado endpoints para salvar listas de entidades para melhorar a performance.

3) Versionamento da API
   - Criado versionamento (v1) para os controllers das entidades.


## Observa��es

- Para gerar as classes QEntity (entidades utilizadas pela querydsl) � necess�rio executar o comando `mvn clean install`.
- Caso a var�avel `currentDate` n�o esteja configurada no Postman, ser� necess�rio cadastr�-la na aba 'Pre-request Script' com o valor de `pm.environment.set('currentDate', new Date());`.