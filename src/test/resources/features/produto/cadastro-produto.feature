# language: pt

Funcionalidade: Cadastrar produto
  Cenario: cadastrar produto com valores
    Dado que possuo um produto com descricao, codigo de barras e preco
    Quando realizo o cadastro do produto
    Entao produto foi criado

  Cenario: cadastrar produto sem descricao
    Dado que possuo um produto sem descricao
    Quando realizo o cadastro do produto
    Entao deve retornar falha, com status 400
    E resposta mostre que descricao esta nulo

  Cenario: cadastrar um produto em duplicidade
    Dado que eu ja possua o produto cadastrado com codigo de barras
    Quando realizo o cadastro do produto
    Entao deve retornar falha, com status 409
    E mensagem seja igual a "Product already exists"