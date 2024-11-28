package br.ada.ecommerce.test.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;

public class RestAssuredUtil {

    public static RequestSpecification produces() {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "token");
    }

    public static void main(String[] args) {
        var response = RestAssured.given()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header(HttpHeaders.AUTHORIZATION, "token")
                .get("/customers");

        response.prettyPrint();
        response.then().statusCode(200);
    }

}
