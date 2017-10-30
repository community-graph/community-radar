package kudos.api.endpoints.pub

import com.jayway.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.junit.Ignore
import org.junit.Test
import radar.ControllerTest

class StatsControllerIntegrationTests : ControllerTest() {

    @Test
    fun shouldReturn200() {

        RestAssured.given()
                .header("content-type", "application/json")
                .get("/stats/tweets/month").peek().then()
                .assertThat().statusCode(200)
    }

    @Test @Ignore("investigate")
    // https://stackoverflow.com/questions/13803316/access-elements-of-an-anonymous-array-via-jsonpath-in-restassured
    // https://github.com/rest-assured/rest-assured/wiki/Usage#anonymous-json-root-validation
    fun shouldReturnData() {
        RestAssured.given()
                .header("content-type", "application/json")
                .get("/stats/tweets/month").peek().then()
                .assertThat().body("2017-09-30", CoreMatchers.hasItem(516))
    }

}
