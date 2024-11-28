# language: pt

Funcionalidade: Cadastrar
  Cenario: cliente ainda nao cadastrado
    Dado cliente nao cadastrado
    Quando realizo o cadastro
    E exibe resultado da requisicao
    Entao cliente foi cadastrado com sucesso
    E cliente deve possuir id