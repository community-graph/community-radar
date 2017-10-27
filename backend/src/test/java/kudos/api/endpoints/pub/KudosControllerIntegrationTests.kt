package kudos.api.endpoints.pub

import com.jayway.restassured.RestAssured
import kudos.api.endpoints.ControllerTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value

class KudosControllerIntegrationTests : ControllerTest() {

    @Autowired
    @Value("@{api.key}") lateinit var apiKey: String

    @Test
    fun shouldReturnForSpecifiedId() {

        RestAssured.given()
                .header("API-Key", apiKey)
                .header("content-type", "application/json")
                .get("/kudos/for/abreslav").peek().then()
                .assertThat().statusCode(200)
    }

    @Test
    fun shouldReturnRandom() {

        RestAssured.given()
                .header("API-Key", apiKey)
                .header("content-type", "application/json")
                .get("/kudos/random").peek().then()
                .assertThat().statusCode(200)
    }

}