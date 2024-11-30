# language: pt

Funcionalidade: consulta de cliente
  Cenario: dado que possuo o cliente cadastro, devo consultar com sucesso
    Dado cliente ja cadastrado na base
    Quando realizo a consulta por nome
    Entao devo receber o cliente
    E nome seja igual ao pesquisado
    E resposta da requisicao tenha status igual a 200

  Cenario: dado que cliente nao esta cadastrado
    Dado cliente nao cadastrado
    Quando realizo o cadastro
    E realizo a consulta por nome
    Entao devo receber o cliente
    E resposta da requisicao tenha status igual a 200