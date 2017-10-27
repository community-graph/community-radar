package kudos.repositories.bolt

import kudos.domain.model.persistent.Kudos
import kudos.domain.model.persistent.Tweet
import kudos.domain.model.persistent.User
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Value
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
class KudosRepository(private val driver: Driver) {


    fun getKudos(twitterId: String): Kudos? {
        val query = """
            match (c:Tweet:Content)-[r:MENTIONED]-(u:User {screen_name: {screen_name}}) return c, u
            """

        var user: User? = null
        val tweets = ArrayList<Tweet>()

        driver.session().use { session ->
            val tx = session.beginTransaction()
            val result = tx.run(query, mapOf(
                    "screen_name" to twitterId
            ))

            while (result.hasNext()) {
                val record = result.next()
                if (user == null) {
                    user = user(record.get("u"))
                }
                tweets.add(tweet(record.get("c")))
            }
            tx.success()
        }
        return Kudos(user!!, tweets)
    }

    private fun user(u: Value) = User(
            screeName = u.get("screen_name").asString(),
            name = u.get("name").asString(),
            location = u.get("location").asString(),
            imageUrl = URL(u.get("profile_image_url").asString()),
            followers = u.get("followers").asInt(),
            following = u.get("following").asInt())

    private fun tweet(c: Value) = Tweet(
            text = c.get("text").asString(),
            created = Date(c.get("created").asLong()),
            favorites = c.get("favorites").asInt())
}


