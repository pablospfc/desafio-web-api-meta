## Sobre a API

Este projeto é uma API desenvolvida como parte do teste para Desenvolvedor Java Remoto da META. É uma API para gerenciamento de contatos. Foi desenvolvida utilizando Java e Jersey.

## Funcionalidades

Os endpoints providos por esta API são:

* Criar um novo contato: `POST /rest-contato/rest/contato`
* Atualizar um contato na API: `PUT /rest-contato/rest/contato/:id`
* Deletar um contato na API: `DELETE /rest-contato/rest/contato/:id`
* Listar contatos: `GET /rest-contato/rest/contato`
* Listar contatos (com paginação): `GET /rest-contato/rest/contato?page=10&page=0`
* Busca um contato específico pelo Id: `GET /rest-contato/rest/contato/:id`

### Detalhes dos Endpoints

`POST /rest-contato/rest/contato`

Este endpoint é utilizado para criar um novo contato.

**Payload body:**

```json
{
 "nome": "Carlos Garcia",
 "canal": "Telefone Residencial",
 "valor": "32587572"
}
```

`PUT /rest-contato/rest/contato/:id`

Este endpoint é utilizado para atualizar um determinado contato.

**Payload body:**

```json
{
 "nome": "David Acosta",
 "canal": "Celular",
 "valor": "98987920815",
 "obs": "Serve como whatsapp também"
}
```

`DELETE /rest-contato/rest/contato/:id`

Este endpoint deleta um contato especificado a partir do id informado.

**Onde:**

`id` - id do contato desejado.

`GET /rest-contato/rest/contato`

Este endpoint retorna uma lista de contatos, limitando como proposto no desafio de 10 registros por página.

`GET GET /rest-contato/rest/contato?size=10&page=0`

Este endpoint retorna uma lista de contatos. Aceita algums parâmetros:

`size` - total de registros por página

`page` - página desejada no resultado

`GET /rest-contato/rest/contato/:id`

Este endpoint retorna um contato especificado a partir do id informado.

**Onde:**

`id` - id do contato desejado.

### Tecnologias Utilizadass

Este projeto foi desenvolvida com as ferramentas:

* **Java 11**
* **Maven**
* **Jersey**
* **Apache Tomcat 9** 
