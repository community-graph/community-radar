package radar

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.restassured.RestAssured
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(ApplicationConfig::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ControllerTest {

    @Autowired lateinit var mapper: ObjectMapper

    @LocalServerPort
    var port: Int? = null

    @Before
    fun setup() {
        RestAssured.baseURI = "http://127.0.0.1"
        RestAssured.port = port!!
    }
}
