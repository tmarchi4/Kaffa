Projeto utilizado para realizar o teste técnico: Kaffa Mobile - Pre-qualification test (v1.10) - Codex

# Visão geral

O projeto é uma aplicação back-end Java, utilizando do framework Spring Boot, solucionando os problemas propostos através de APIs

 
# Setup da aplicação (local)

## Pré-requisito

Antes de rodar a aplicação é preciso garantir que as seguintes dependências estejam corretamente instaladas:
```
Java 21
Maven 3.3.3 
```

## Preparando ambiente

Não é necessário a criação da base de dados relacional no MYSQL, pois como trata-se de um teste, está o banco de dados hospedado, mesmo rodando local.

## Instalação da aplicação

Primeiramente, faça o clone do repositório:
```
https://github.com/tmarchi4/Kaffa.git
```
Feito isso, acesse o projeto:
```
cd Kaffa
```
É preciso compilar o código e baixar as dependências do projeto:
```
mvn clean package
```
Finalizado esse passo, vamos iniciar a aplicação:
```
mvn spring-boot:run
```
Pronto. A aplicação está disponível em http://localhost:8080
```
Tomcat started on port(s): 8080 (http)
Started AppConfig in xxxx seconds (JVM running for xxxx)
```


# APIs

O projeto disponibiliza alguns endpoints diferentes, como: CheckCNPJ, Rectangle, ToDoList e WorldClock, onde utilizam o padrão Rest de comunicação, produzindo e consumindo arquivos no formato JSON.

Segue abaixo as APIs disponíveis no projeto (via Postman):

[Teste Codex.postman_collection.json](https://github.com/user-attachments/files/17428313/Teste.Codex.postman_collection.json)

[Teste Codex Localhost.postman_collection.json](https://github.com/user-attachments/files/17428342/Teste.Codex.Localhost.postman_collection.json)
