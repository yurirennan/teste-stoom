<h1 align="center" > DESAFIO <b style="color: #03A9F5;">STOOM</b> </h1>

<h3> :loudspeaker: O que é o projeto? </h3>
<p>O desafio consiste em criar uma API REST para cadastro de produtos desenvolvido em Java com framework Spring.</p>

<hr>

<h3 id="sumario"> 📑 Sumário </h3>

- <a href="#requisitos"> Requisitos para rodar o projeto:</a>
  - <a href="#requisitos-com-docker"> Com Docker.</a>
  - <a href="#requisitos-sem-docker"> Sem Docker.</a>
- <a href="#como-rodar">Como rodar o projeto:</a>
   - <a href="#rodar-com-docker"> Com Docker.</a>
   - <a href="#rodar-sem-docker"> Sem Docker.</a>
- <a href="#tecnologias">Tecnologias e recursos utilizadas no projeto.</a>

<hr>

<h3 id="requisitos"> 🧾 Requisitos para rodar o projeto</h3>

<h4 id="requisitos-com-docker"> Com Docker:</h4> 

- <a target="_blank" href="https://maven.apache.org/">Maven</a>
- <a target="_blank" href="https://openjdk.java.net/install/">Java 8</a>
- <a target="_blank" href="https://docs.docker.com/engine/install/">Docker</a>
- <a target="_blank" href="https://docs.docker.com/compose/install/">Docker Compose</a>

<h4 id="requisitos-sem-docker"> Sem Docker:</h4>

- <a target="_blank" href="https://maven.apache.org/">Maven</a>
- <a target="_blank" href="https://openjdk.java.net/install/">Java 8</a>
- <a target="_blank" href="https://www.postgresql.org/download/">Postgresql</a>
- <a target="_blank" href="https://redis.io/downloads/">Redis</a>

<hr>

<h3 id="como-rodar"> 🏗️ Rodar o projeto</h3>

<h4 id="rodar-com-docker"> Com Docker:</h4> 

- Gere o <b>.jar</b> da aplicação executando o comando no terminal:
    ```shell
    mvn clean install -Dskiptests
    ```

- Instancie e rode os containers executando o comando no terminal:
    ```shell
    docker-compose up --build --force-recreate
    ```

<h4 id="rodar-sem-docker"> Sem Docker:</h4> 

- Gere o <b>.jar</b> da aplicação executando o comando no terminal:
    ```shell
    mvn clean install -Dskiptests
    ```

- Para rodar o <b>.jar</b> vá até a pasta do projeto pelo terminal e rode o comando:
    ```shell
    java -jar app.jar
     ```

<h3 id="tecnologias"> 🚀 Tecnologias e recursos utilizadas no projeto</h3>

- Java
- Banco de Dados Postgresql
- Bando de Dados Redis para Cache
- Docker
- Spring Web
- Hibernate Validator
- Swagger
<hr>

<h3 id="documentacao"> 🧾️ Acesso a documentação da API</h3>

<h4 id="acesso"> Documentação:</h4> 

- Quando a aplicação estiver executando acesse:
    ```shell
    http://localhost:8080/swagger-ui/
    ```



**Seja bem-vindo candidato!**

Como um desenvolvedor Back-End na Stoom uma das maiores responsabilidades que você vai ter é desenvolver funcionalidades e corrigir bugs em sistemas de e-commerce de larga escala que utilizam Spring Boot. Com base nisso, precisamos de sua ajuda para construir a nossa loja Stoom, que deve conter as seguintes funcionalidades:

1. Deve ser desenvolvida uma API de CRUD de produtos
2. Os produtos devem ser enriquecidos com as informações que você julgar relevante para o funcionamento em uma loja, algumas são obrigatórias:
    - Categorias
    - Marca
    - Preços
3. Deve existir um endpoint na API para a busca de produtos que será utilizada na loja
4. Deve existir um endpoint que lista os produtos de uma determinada Marca
5. Deve existir um endpoint que lista os produtos de uma determinada Categoria
6. Produtos podem ser inativados para não aparecerem na busca ou nas listagens sem a necessidade de serem deletados para poderem ser reativados posteriormente
7. Marcas e categorias também podem ser inativados para não aparecerem na loja

**Informações relevantes**:
- Atente-se à todos os pré-requisitos estabelecidos, porém não limite-se a eles, ideias novas ou melhorias são sempre bem-vindas :smiley:
- Você tem total liberdade para fazer qualquer tipo de alteração em qualquer ponto do código (contanto que não alterem a maneira de execução da aplicação)
- Se possível, adicione uma collection do Postman no repositório para conseguirmos testar o código da mesma forma que você
- Boas práticas, legibilidade, testes e performance são alguns dos pontos que serão considerados durante a avaliação

**Boa sorte!**
