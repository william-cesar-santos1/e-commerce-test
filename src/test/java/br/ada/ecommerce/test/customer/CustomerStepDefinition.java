package br.ada.ecommerce.test.customer;

import br.ada.ecommerce.test.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class CustomerStepDefinition {

    private Response response = null;
    private CustomerDto customer = new CustomerDto();

    @Dado("cliente nao cadastrado")
    public void clienteNaoCadastrado() {
        customer = new CustomerDto();
        customer.setName(RandomStringUtils.randomAlphabetic(20));
        customer.setDocument(RandomStringUtils.randomNumeric(11));
        customer.setBirthDate("1989-02-06");
        customer.setTelephone(List.of(RandomStringUtils.randomNumeric(11)));
        customer.setEmail(List.of("endtoend@test.com"));
    }

    @Quando("realizo o cadastro")
    public void cadastrar() {
        response = RestAssuredUtil.produces()
                .body(customer)
                .when().post("/customers");
    }

    @Entao("cliente foi cadastrado com sucesso")
    public void sucesso() {
        response.then().statusCode(HttpStatus.SC_CREATED);
    }

    @E("exibe resultado da requisicao")
    public void exibeResponse() {
        response.prettyPrint();
    }

    @E("cliente deve possuir id")
    public void validaPossuiId() {
        var id = response.jsonPath().get("id");
        Assertions.assertNotNull(id);
    }

}
