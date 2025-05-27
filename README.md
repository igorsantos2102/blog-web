# BlogWeb Backend

Este repositório contém o **backend** da aplicação **BlogWeb**, construído com **Spring Boot**, **MySQL** e **JWT** para autenticação.

---

## 📋 Pré-requisitos

* Java 17 ou superior
* Maven 3.6+
* MySQL 8+
* (Opcional) Postman ou Insomnia para testar as APIs

---

## 🚀 Tecnologias

* Spring Boot 3.5.0
* Spring Security com JWT
* Spring Data JPA (Hibernate)
* MySQL (conector JDBC)
* Lombok
* Jakarta Persistence

---

## 📦 Instalação e Configuração

1. Clone este repositório:

   ```bash
   git clone https://github.com/igorsantos2102/blog-web.git
   cd blog-web
   ```

2. Configure o banco de dados MySQL:

   * Crie uma database, por exemplo `blogdb`.
   * No arquivo `src/main/resources/application.properties`, ajuste:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/blogdb?useSSL=false&serverTimezone=UTC
     spring.datasource.username=SEU_USUARIO
     spring.datasource.password=SUA_SENHA
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Compile e execute o backend:

   ```bash
   mvn clean spring-boot:run
   ```

O servidor ficará disponível em `http://localhost:8080`

---

## 🔐 Autenticação JWT

* **POST** `/authenticate`

  * Body: `{ "email": "usuario@exemplo.com", "password": "senha" }`
  * Retorna: `{ "jwtToken": "<token>", "username": "usuario@exemplo.com", "name": "Nome Completo" }`

* **POST** `/sign-up`

  * Body: `{ "name": "Nome Completo", "email": "usuario@exemplo.com", "password": "senha" }`
  * Retorna: HTTP 201 ou mensagem de erro.

Para todos os endpoints `GET`, `PUT`, `POST` e `DELETE` em `/api/posts` e `/api/comments`, inclua o header:

```
Authorization: Bearer <jwtToken>
```

---

## 📝 Endpoints Principais

### Postagens

| Método | Endpoint                   | Descrição                                    |
| ------ | -------------------------- | -------------------------------------------- |
| POST   | `/api/posts`               | Criar nova postagem                          |
| GET    | `/api/posts`               | Listar todas as postagens                    |
| GET    | `/api/posts/{id}`          | Buscar post por ID                           |
| PUT    | `/api/posts/{id}`          | Atualizar postagem (somente autor)           |
| DELETE | `/api/posts/{id}`          | Excluir postagem (somente autor)             |
| PUT    | `/api/posts/{id}/like`     | Curtir uma postagem                          |
| GET    | `/api/posts/search/{name}` | Buscar postagens por palavra-chave no título |
| GET    | `/api/posts/tag-stats`     | Estatísticas de tags (tag e contagem)        |

### Comentários

| Método | Endpoint                 | Descrição                                   |
| ------ | ------------------------ | ------------------------------------------- |
| POST   | `/api/comments/create`   | Criar comentário em `postId` especificado   |
| GET    | `/api/comments/{postId}` | Listar todos os comentários de uma postagem |

---

## 🔧 Próximos Passos

* Documentar e versionar API com Swagger/OpenAPI
* Adicionar testes de integração
* Implementar CRUD de usuários (roles e perfis)
* Configurar CI/CD (GitHub Actions)
* Deploy em ambiente de produção (Heroku, Railway, etc.)

---

## 📫 Contato

- LinkedIn: [igor-santos](https://www.linkedin.com/in/0-igor-santos)

