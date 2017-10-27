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
    fun shouldReturnKudosForFeaturedCommunityMember() {

        RestAssured.given()
                .header("API-Key", apiKey)
                .header("content-type", "application/json")
                .get("/kudos/member/1234").peek().then()
                .assertThat().statusCode(200)
    }

}