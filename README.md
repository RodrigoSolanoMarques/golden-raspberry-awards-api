## Golden Raspberry Awards - API

Esta API RESTful foi desenvolvida para possibilitar obter o produtor com maior intervalo entre dois prêmios consecutivos e o que
obteve dois prêmios mais rápido da categoria Pior Filme do Golden Raspberry Awards.

### Desenvolvimento
* Java - 17;
* Spring Boot - 2.7.6
  * Spring Data JPA; 
  * Spring Batch - Leitura do arquivo .CSV;
* Banco de Dados H2.

### Execução do Projeto
* Fazer o clone deste projeto;
* Abrir em uma IDE de sua preferência preparada para abrir projetos Maven;
* Configurar a classe GoldenRaspberryAwardsApiApplication.java como a classe principal do projeto e a executar;
* Toda vez que o projeto for executado automaticamente o arquivo que se encontra no diretório file/movielist.csv será lido e suas informações gravadas no banco H2.

#### Execução dos Teste de Integração
Executar a classe GoldenRaspberryAwardsApiApplicationTests

### ENDPOINT
Acesse: http://localhost:8080/film/min-max-winners-interval

### Banco de Dados H2
Acesse: http://localhost:8080/h2-console (é necessário que o projeto esteja rodando).

#### Parâmetros de acesso ao banco de dados
* JDBC URL: jdbc:h2:mem:golden_raspberry_awards_db 
* Usuário: sa
* Senha: Não tem senha, deixa-a em branco.