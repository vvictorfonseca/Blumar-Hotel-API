﻿# Blumar-Hotel-API
 
 ##  :clipboard: Descrição

Blumar Hotel é uma API de gerenciamento de hotel.

***
 
 ## :computer:	 Tecnologias e Ferramentas usadas

- Java
- Spring Boot
- JPA
- Javax Validation
- MySql
***

##  :hammer: Principais Funcionalidades

- É possível criar novos quartos para o Hotel.
- É possível criar um novo check-in, caso seja efetivado o quarto alugado fica desabilitado para novas reservas.
- Como cliente, é possível fazer compras de produtos durante a estadia.
- Como cliente, é possível checar as compras efetuadas durante a estadia.
- Opção de checkOut. Retornando os dias de estadia do cliente, produtos adquiridos e o valor total da estadia (valor do quarto + valor produtos).
***

API:

```bash
- POST /room/create
    - Rota para criar uma novo quarto para o hotel. Informado o tipo de acomodação (Individual, Acompanhante ou Criança), o número do quarto, e se está disponível. O preço do quarto será inserido na tabela dinamicamente de acordo com o tipo de quarto informado.
    - body: 
    {
        "type": "Individual,
        "number": 202,
        "available": true
    }
```
```bash
- POST /checkIn/create
    - Rota para criar um novo checkIn. Será retorando informações do hotel e do quarto do hóspode
    - body: 
    {
      "room": {
        "id": 1
        },
      "roomType": "Individual",
      "clientName": "Frederico Cunha",
      "checkoutDate": "2022-11-20"
    }
```
```bash
- Post /purchase/create/
    - Rota para o cliente fazer compra de algum produto (Água, Refrigerante, Cerveja). O preço do produto é inserido na tabela dinamicamente de acordo com o produto inserido.
    - body: 
    {
      "checkIn": {
       "id": 1
       },
      "productName": "Cerveja"
    }
```
```bash
- GET /purchase/get/{id}
    - Rota para listar todos os produtos que o cliente adquiriu durante a estadia.
    - Informar o id do checkin no parâmetro da rota.
```
```bash
- GET /checkOut/{id}
    - Rota para efetuar o checkOut de um cliente.
    - Informar o id do checkin no parâmetro da rota.
```
