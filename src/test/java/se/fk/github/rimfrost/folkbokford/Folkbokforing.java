package se.fk.github.rimfrost.folkbokford;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

@QuarkusTest
class FolkbokforingTest
{
   @Test
   void testFolkbokforing()
   {
      String actualResponse = given()
            .when().get("/folkbokforing/19900716-1234")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString();

      assertThat(actualResponse).isEqualToIgnoringWhitespace("""
                        {
              "id": "19900716-1234",
              "fornamn": "Lisa",
              "efternamn": "Tass",
              "kon": "K",
              "adress": {
                "careOf": "C/o Andersson",
                "utdelningsadress": "Försäkringsgatan 137",
                "postnummer": "12345",
                "postort": "Luleå"
              }
            }
            """);
   }

   @Test
   void testFolkbokforingReturns404WhenPersnrEndsWith9999()
   {
      given()
            .when().get("/folkbokforing/19900716-9999")
            .then()
            .statusCode(404);
   }

}
