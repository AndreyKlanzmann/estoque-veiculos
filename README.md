# Sistema Automotivo – Gestão de Estoque de Veículos

API REST feita com Java e Spring Boot para cadastrar, consultar, filtrar, atualizar e remover
veículos de um estoque. Os dados ficam em um banco MySQL.

## Tecnologias

- Java 17
- Spring Boot 3.3 (Web, Data JPA, Validation)
- MySQL 8
- Maven

## Como rodar

1. Instale o Java 17 e o MySQL, e deixe o MySQL rodando na porta 3306.
2. Confira o usuário e a senha em `src/main/resources/application.properties`
   (por padrão `root` / `root`). Também dá para usar as variáveis de ambiente `DB_USER` e `DB_PASSWORD`.
3. O banco `estoque_veiculos` é criado automaticamente. Se preferir criar na mão, use o arquivo `sql/schema.sql`.
4. Na pasta do projeto, rode:

```
mvn spring-boot:run
```

5. A API sobe em `http://localhost:8080`. A tela simples de listagem e filtros fica em `http://localhost:8080/`.

## Estrutura de pastas

```
src/main/java/com/estoque/veiculos
 ├── controller   -> recebe as requisições HTTP
 ├── service      -> regras de negócio
 ├── repository   -> acesso ao banco (Spring Data JPA)
 ├── model        -> entidades (Marca, Modelo, Veiculo, StatusVeiculo)
 ├── dto          -> objetos de entrada e saída da API
 └── exception    -> tratamento de erros
src/main/resources
 ├── application.properties
 └── static/index.html   -> front-end simples
sql/schema.sql           -> modelo físico do banco
```

## Endpoints

| Método | Rota | O que faz |
|--------|------|-----------|
| POST | /marcas | Cadastra marca |
| GET | /marcas | Lista marcas |
| GET | /marcas/{id} | Busca marca |
| PUT | /marcas/{id} | Atualiza marca |
| DELETE | /marcas/{id} | Remove marca |
| POST | /modelos | Cadastra modelo (informa `marcaId`) |
| GET | /modelos?marcaId= | Lista modelos (filtro opcional por marca) |
| GET | /modelos/{id} | Busca modelo |
| PUT | /modelos/{id} | Atualiza modelo |
| DELETE | /modelos/{id} | Remove modelo |
| POST | /veiculos | Cadastra veículo |
| GET | /veiculos | Lista veículos (com filtros opcionais) |
| GET | /veiculos/{id} | Busca veículo |
| PUT | /veiculos/{id} | Atualiza veículo (preço, quilometragem, status, etc.) |
| DELETE | /veiculos/{id} | Remove veículo |

Filtros de `GET /veiculos` (todos opcionais, podem ser combinados):
`marcaId`, `modeloId`, `precoMin`, `precoMax`, `ano`, `status` (`DISPONIVEL`, `VENDIDO` ou `DESCONTINUADO`).

Exemplo: `GET /veiculos?marcaId=1&ano=2020&status=DISPONIVEL&precoMax=80000`

## Exemplos de JSON

Criar marca (`POST /marcas`):

```json
{ "nome": "Toyota" }
```

Criar modelo (`POST /modelos`):

```json
{ "nome": "Corolla", "marcaId": 1 }
```

Criar veículo (`POST /veiculos`):

```json
{
  "modeloId": 1,
  "anoFabricacao": 2020,
  "cor": "Prata",
  "preco": 95000.00,
  "quilometragem": 42000,
  "status": "DISPONIVEL"
}
```

## Códigos de resposta

- 201 – criado com sucesso
- 200 – consulta ou atualização feita
- 204 – removido
- 400 – dados inválidos
- 404 – registro não encontrado
- 409 – registro duplicado ou em uso (ex.: apagar uma marca que ainda tem modelos)
