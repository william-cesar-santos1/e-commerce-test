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

import java.util.List;

public class ClienteStepDefinition {

    private Response response;
    private ClienteDto cliente;

    @Dado("cliente nao cadastrado")
    public void clienteNaoCadastrado() {
        cliente = new ClienteDto();
        cliente.setNome(RandomStringUtils.randomAlphabetic(20));
        cliente.setDocumento(RandomStringUtils.randomNumeric(11));
        cliente.setDataNascimento("1989-02-06");
        cliente.setTelefone(List.of(RandomStringUtils.randomNumeric(11)));
        cliente.setEmail(List.of("endtoend@test.com"));
    }

    @Quando("realizo o cadastro")
    public void cadastrar() {
        response = RestAssuredUtil.produces()
                .body(cliente)
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

    @Dado("cliente ja cadastrado na base")
    public void clienteJaCadastrado() {
        cliente = new ClienteDto();
        cliente.setNome(RandomStringUtils.randomAlphabetic(50));
        cliente.setDocumento("000000");
        cliente.setEmail(List.of("william_cesar_santos@hotmail.com"));
        RestAssuredUtil.produces()
                .body(cliente)
                .when().post("/customers");
    }

    @Quando("realizo a consulta por nome")
    public void realizarConsulta() {
        response = RestAssuredUtil.produces()
                .when().get("/customers?name=" + cliente.getNome());
        response.prettyPrint();
    }

    @Entao("devo receber o cliente")
    public void validaClienteEncontrado() {
        var id = response.jsonPath().get("id");
        Assertions.assertNotNull(id);
    }

    @E("nome seja igual ao pesquisado")
    public void validaNome() {
        var nome = response.jsonPath().get("[0].name");
        Assertions.assertEquals(cliente.getNome(), nome);
    }

    @E("resposta da requisicao tenha status igual a {int}")
    public void validarStatua(int status) {
        response.then().statusCode(status);
    }

}
