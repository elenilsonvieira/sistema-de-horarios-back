## Cuidado, branch master
# Sistema de horários
# Sobre o sistema

*O sistema de horários traz consigo o propósito de simplificar o gerenciamento de horários das turmas e disciplinas presentes na Instituição.
A ideia do sistema partiu graças à visão de oportunidades, pois o que era obtido apenas através de e-mails ou quadros de aviso pelos corredores, agora,
através da ferramenta, poderá ser visualizado a partir de qualquer dispositivo.*
## Das Tecnologias:
- Java
- Spring boot
- Spring data
- Spring MVC
- PostgreSQL
- Heroku
- Docker
- Docker-compose
## Executando o projeto

Antes de tudo, tenha o docker e o docker-compose instalado na sua máquina  
Para baixar o docker, [Clique aqui](https://docs.microsoft.com/pt-br/virtualization/windowscontainers/manage-docker/configure-docker-daemon)  
Após isso, execute:
```
docker-compose up
```
Agora basta baixar as dependências maven e executar o projeto normalmente

## Observação
Se você quiser se conectar ao seu postgreSQL para fazer operações e visualizar a forma como os dados estarão nas tabelas através do PgAdmin,
basta acessar [esse link](http://localhost:15432) e colocar as seguintes configurações:  
- Host: 172.17.0.1
- Port: 5441
- User: dac
- Password: dac
- Database: shd
Isso é possível porque existem duas imagens no docker-compose responsável por subir dois containers (um container pro banco postgre e outro pro pgAdmin).  
Essa configuração poderá ser visualizada e editada acessando o arquivo docker-compose.yml

## Documentação
- Produção:  [Clique aqui](https://sistema-de-horario.herokuapp.com/swagger-ui.html#/) Para acessar a documentação da API na heroku
- Desenvolvimento  [Clique aqui](http://localhost:8080/swagger-ui.html#/) Para acessar a documentação da API localmente
