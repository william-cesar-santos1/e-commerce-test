package br.ada.ecommerce.test.product;

import br.ada.ecommerce.test.restassured.RestAssuredUtil;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoStepDefinition {

    private Response response;
    private ProductDto produto;

    @Dado("que eu ja possua o produto cadastrado com codigo de barras")
    public void cadastrarProdutoPorCodBarra() {
        var products = RestAssuredUtil.produces()
                .when().get("/products")
                .body().as(new TypeRef<List<ProductDto>>() {
                });
        var codBarras = products.get(0).getBarcode();
        produto = new ProductDto();
        produto.setBarcode(codBarras);
        produto.setDescription(RandomStringUtils.randomAlphabetic(15));
        produto.setPrice(new BigDecimal(RandomStringUtils.randomNumeric(3)));
    }

    @Dado("que possuo um produto sem descricao")
    public void produtoSemDescricao() {
        produto = new ProductDto();
        produto.setBarcode(RandomStringUtils.randomNumeric(11));
        produto.setPrice(new BigDecimal(RandomStringUtils.randomNumeric(3)));
    }

    @Dado("que possuo um produto com descricao, codigo de barras e preco")
    public void productCompleto() {
        produto = new ProductDto();
        produto.setBarcode(RandomStringUtils.randomNumeric(11));
        produto.setDescription(RandomStringUtils.randomAlphabetic(15));
        produto.setPrice(new BigDecimal(RandomStringUtils.randomNumeric(3)));
    }

    @Quando("realizo o cadastro do produto")
    public void cadastrar() {
        response = RestAssuredUtil.produces()
                .body(produto)
                .when().post("/products");
    }

    @Entao("deve retornar falha, com status {int}")
    public void deveFalharARequest(int status) {
        response.then().statusCode(status);
    }

    @Entao("produto foi criado")
    public void produtoCriado() {
        response.then().statusCode(201);
    }

    @E("resposta mostre que descricao esta nulo")
    public void validarNomeNulo() {
        var mensagem = response.jsonPath().get("errors[0].description");
        Assertions.assertEquals("must not be null", mensagem);
    }

    @E("mensagem seja igual a {string}")
    public void validarMensagem(String expectedMessage) {
        var mensagem = response.jsonPath().get("message");
        Assertions.assertEquals(expectedMessage, mensagem);
    }

}
