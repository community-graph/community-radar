package radar.kudos.api.endpoints.pub

import com.jayway.restassured.RestAssured
import org.junit.Test
import radar.ControllerTest

class KudosControllerIntegrationTests : ControllerTest() {

    @Test
    fun shouldReturnForSpecifiedId() {

        RestAssured.given()
                .header("content-type", "application/json")
                .get("/kudos/for/abreslav").peek().then()
                .assertThat().statusCode(200)
    }

    @Test
    fun shouldReturnRandom() {

        RestAssured.given()
                .header("content-type", "application/json")
                .get("/kudos/random").peek().then()
                .assertThat().statusCode(200)
    }

}
