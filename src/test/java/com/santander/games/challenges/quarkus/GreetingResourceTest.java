package com.santander.games.challenges.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

  @Test
  void testHelloEndpoint() {
    given()
            .when().get("/hello")
            .then()
            .statusCode(200)
            .body(is("Hello Challenge 5!"));
  }

  @Test
  void testHelloNameEndpoint() {
    String name = "JAntonio";

    given()
            .when().get("/hello/{name}", name)
            .then()
            .statusCode(200)
            .body(is("Hello " + name + "! This is challenge 5!"));
  }


  @Test
  void testGoodbyeEndpoint() {
    given()
            .when().get("/goodbye")
            .then()
            .statusCode(200)
            .body(is("Goodbye Challenge 5!"));
  }


  @Test
  void testGoodbyeNameEndpoint() {

    String name = "JAntonio";

    given()
            .when().get("/goodbye/{name}", name)
            .then()
            .statusCode(200)
            .body(is("Goodbye " + name + "!"));
  }


}