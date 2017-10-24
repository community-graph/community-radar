package kudos.api.endpoints.pub

import com.jayway.restassured.RestAssured
import kudos.api.endpoints.ControllerTest
import kudos.api.service.authorization.dto.CredentialsDTO
import org.hamcrest.core.IsNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value


class AuthorizationIntegrationTests : ControllerTest() {

    @Autowired @Value("@{api.key}") lateinit var apiKey: String

    @Test
    fun authorize_withUserNameAndPassword() {

        val testCredentials = mapper.writeValueAsString(
                CredentialsDTO(email = "jasper@appsquick.ly", password = "password"))

        println(testCredentials)

        RestAssured.given()
                .header("content-type", "application/json")
                .body(testCredentials)
                .post("/authorization").peek().then()
                .body("accessToken", IsNull.notNullValue())
    }


}