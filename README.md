# spring-boot-assessment

### Objetivo
Criar um projeto Spring utilizando Java 8 que permita informar a partir de um formulário HTML uma sequência de parênteses, colchetes e chaves e que seja verificado se abrem e fecham corretamente; e uma API Rest que tenha o conceito de CRUD para um relacionamento One to Many (Uma pessoa possui um ou mais contatos).

### Ambiente
Criou-se um projeto web utilizando Spring Boot, a partir do [Spring Initializr](https://start.spring.io/); com as seguintes dependências:
- DevTools
- WebStarter
- Thymeleaf
- DataJPA
- MySQLDriver

Foi utilizado como banco de dados o MySQL e a configuração se encontra no arquivo `application.properties` em [src/main/resources](https://github.com/hiroshisogabe/spring-boot-assessment/tree/master/src/main/resources)

### Executando
Utilizando a IDE Eclipse para desenvolvimento, a aplicação roda facilmente executando o arquivo [AssessmentApplication.java](https://github.com/hiroshisogabe/spring-boot-assessment/tree/master/src/main/java/br/com/sogabe/assessment) como uma aplicação java (Run As Java Application).

Outra maneira de rodar a aplicação, é através do [maven](https://maven.apache.org/) na raiz do projeto:
`mvn spring-boot:run`

Em ambas as opções é verificado que por padrão, a aplicação rodará em http://localhos:8080 
