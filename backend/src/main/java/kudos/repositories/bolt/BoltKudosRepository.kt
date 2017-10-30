package kudos.repositories.bolt

import kudos.domain.model.persistent.entities.pojo.Kudos
import kudos.domain.model.persistent.entities.pojo.Tweet
import kudos.domain.model.persistent.entities.pojo.User
import kudos.repositories.api.KudosRepository
import kudos.utils.hashTags
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Value
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
class BoltKudosRepository(private val driver: Driver) : KudosRepository {


    override fun getByTwitterId(twitterId: String): Kudos? {
        val query = """
            match (sender:User)-[:POSTED]->(tweet:Tweet:Content)-[:MENTIONED]-(member:User {screen_name:{screen_name}})
            return sender, tweet, member;
            """

        var user: User? = null
        var sender: String? = null
        val tweets = ArrayList<Tweet>()

        driver.session().use { session ->
            val tx = session.beginTransaction()
            val result = tx.run(query, mapOf(
                    "screen_name" to twitterId
            ))

            while (result.hasNext()) {
                val record = result.next()
                if (user == null) {
                    user = user(record.get("member"))
                }
                if (sender == null) {
                    sender = record.get("sender").get("screen_name").asString()
                }
                tweets.add(tweet(record.get("tweet"), sender!!))
            }
            tx.success()
        }
        return Kudos(user!!, tweets)
    }

    override fun getRandom(): Kudos? {
        val query = """
            match (s:User)-[:POSTED]->(c:Tweet:Content)-[r:MENTIONED]-(u:User) where exists(u.profile_image_url)
            WITH u.screen_name as screen_name, count(r) as mentionCount where mentionCount >= 4
            return screen_name limit 1000;
            """

        val screenNames = ArrayList<String>()

        driver.session().use { session ->
            val tx = session.beginTransaction()
            val result = tx.run(query, emptyMap())

            while (result.hasNext()) {
                val record = result.next()
                screenNames.add(record.get("screen_name").asString())
            }
            tx.success()
        }

        return when (screenNames.size) {
            0 -> null
            else -> getByTwitterId(screenNames[(Math.random() * screenNames.size).toInt()])
        }
    }

    private fun user(u: Value) = User(
            screenName = u.get("screen_name").asString(),
            name = u.get("name").asString(),
            description = null,
            location = u.get("location").asString(),
            imageUrl = url(u.get("profile_image_url")),
            followers = u.get("followers").asInt(),
            following = u.get("following").asInt())

    private fun tweet(c: Value, sender: String) = Tweet(
            sender = sender,
            text = c.get("text").asString(),
            created = Date(c.get("created").asLong()),
            favorites = c.get("favorites").asInt(),
            hashTags = c.get("text").asString().hashTags())

    private fun url(u: Value): URL? {
        return when {
            u.isEmpty -> null
            else -> URL(u.asString())
        }
    }

}


