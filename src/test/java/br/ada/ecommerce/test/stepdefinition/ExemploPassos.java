package br.ada.ecommerce.test.stepdefinition;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.jupiter.api.Assertions;

public class ExemploPassos {

    private boolean estouFeliz = false;

    @Dado("que eu estou com fome")
    public void estouComFome() {
        System.out.println("estouComFome");
        estouFeliz = false;
    }

    @Quando("eu como {string}")
    public void comerPizza(String comida) {
        System.out.println(comida);
        if (!comida.equalsIgnoreCase("chuchu")) {
            estouFeliz = true;
        }
    }

    @E("tomo uma coca {string}")
    public void tomarCoquinha(String tipo) {
        estouFeliz = !tipo.equalsIgnoreCase("zero");
    }

    @Entao("eu estarei feliz")
    public void estouFeliz() {
        System.out.println("Estou feliz");
        Assertions.assertTrue(estouFeliz);
    }

    @Entao("continuo triste")
    public void estouTriste() {
        Assertions.assertFalse(estouFeliz);
    }

}
