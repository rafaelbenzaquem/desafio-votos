##

## :speech_balloon:O que há neste documento
1. [Missão deste projeto](./README.md#hammermissão-deste-projeto)
2. [Executando testes unitários](./README.md#executando-testes-unitários)
3. [Executando testes de integração](./README.md#executando-testes-de-integração)
4. [Executando todos os testes](./README.md#executando-todos-os-testes)
5. [Executando o projeto com Docker](./README.md#whaleexecutando-o-projeto-com-docker)
6. [Executando o projeto localmente](./README.md#computerexecutando-o-projeto-localmente)
7. [Endpoints disponíveis](./README.md#mag_rightendpoints-disponíveis)

## :hammer:1 - Missão deste projeto

No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta.

Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

## Executando testes unitários

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Ponly-unit-tests
```
no Windows

```shell
mvnw.cmd test -Ponly-unit-tests
```

## Executando testes de integração

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Ponly-integration-tests
```
no Windows

```shell
mvnw.cmd test -Ponly-integration-tests
```

## Executando todos os testes

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw test -Pall-tests
```
no Windows

```shell
mvnw.cmd test -Pall-tests
```

## :whale:Executando o projeto com Docker

No terminal, navegue até a pasta raiz do projeto e execute

```shell
docker-compose up
```

## :computer:Executando o projeto localmente e cobertura de testes com Jacoco

No terminal, navegue até a pasta raiz do projeto e execute

```shell
./mvnw clean install 
```

no Windows

```shell
mvnw.cmd clean install 
```

Após a conclusão, execute subir a aplicação

```shell
./mvnw spring-boot:run
```

no Windows

```shell
mvnw.cmd spring-boot:run
```

Para acessar o relatório de cobertura de testes

- [Relatório de testes com Jacoco ](http://localhost:63342/desafio-votos/target/site/jacoco/index.html?_ijt=rq92eosmcj0gmg1fb0n01svl01&_ij_reload=RELOAD_ON_SAVE)
>  http://localhost:63342/desafio-votos/target/site/jacoco/index.html?_ijt=rq92eosmcj0gmg1fb0n01svl01&_ij_reload=RELOAD_ON_SAVE

## :mag_right:Endpoints disponíveis

Antecedido por http://\<seu-host\>:8080 temos os endpoints

- /associados \(POST\)
- /pautas \(POST\)
- /sessoes \(POST\)
- /votos \(POST\)
- /voto-resultado/pautas/{id_pauta} \(GET\)

- /swagger-ui.html \(Documentação da API\)
