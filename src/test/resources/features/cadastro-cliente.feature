# language: pt

Funcionalidade: Comer
  Cenario: Comendo pizza
    Dado que eu estou com fome
    Quando eu como "pizza"
    Entao eu estarei feliz

  Cenario: Comendo lasanha
    Dado que eu estou com fome
    Quando eu como "lasanha"
    Entao eu estarei feliz

  Cenario: Comendo pao de queijo
    Dado que eu estou com fome
    Quando eu como "pao de queijo"
    E tomo uma coca "normal"
    Entao eu estarei feliz

  Cenario: Comendo chuchu
    Dado que eu estou com fome
    Quando eu como "chuchu"
    E tomo uma coca "zero"
    Entao continuo triste