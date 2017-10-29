package kudos.repositories.bolt

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Assert.*

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KudosRepositoryTest {

    @Autowired lateinit var repositoroy : BoltKudosRepository

    @Test
    fun getKudos() {
        val kudos = repositoroy.getByTwitterId("hhariri")
        assertNotNull(kudos)
        assertNotNull(kudos!!.communityMember)
        assertTrue(kudos.tweets.isNotEmpty())
    }
}