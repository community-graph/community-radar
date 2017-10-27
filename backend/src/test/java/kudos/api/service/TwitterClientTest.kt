package kudos.api.service

import kudos.ApplicationConfig
import kudos.api.service.twitter.TwitterClient
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import org.junit.Assert.*

@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(ApplicationConfig::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TwitterClientTest {

    @Autowired lateinit var twitterClient : TwitterClient

    @Test
    fun testFetchUser() {
        val user = twitterClient.load("abreslav")
        assertEquals("abreslav", user.screenName)
        println(user)
    }
}