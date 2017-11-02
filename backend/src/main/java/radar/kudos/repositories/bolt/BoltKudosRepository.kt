package radar.kudos.repositories.bolt

import radar.kudos.domain.model.persistent.entities.pojo.Kudos
import radar.kudos.domain.model.persistent.entities.pojo.Tweet
import radar.kudos.domain.model.persistent.entities.pojo.User
import radar.kudos.repositories.api.KudosRepository
import radar.kudos.utils.hashTags
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Value
import org.springframework.stereotype.Repository
import java.net.URL
import java.util.*

@Repository
class BoltKudosRepository(private val driver: Driver) : KudosRepository {


    override fun getByTwitterId(twitterId: String): Kudos? {
        val query = """
match (kudos:Tag {name:"kudos"})
match (sender:User)-[:POSTED]->(tweet:Tweet:Content)-[:MENTIONED]-(member:User {screen_name:{screen_name}})
where not tweet:Retweet and tweet.created > timestamp()/1000 - 14*24*3600
with * order by (case when (tweet)-[:TAGGED]->(kudos) then 2 else 1 end) * (tweet.favorites + size((tweet)<-[:RETWEETED]-())) desc limit 10
return sender, tweet, member, [(tweet)-[:TAGGED]-(tag) | tag.name] as tags ORDER BY rand() LIMIT 4;
"""

        val params = mapOf("screen_name" to twitterId)

        return retrieveKudos(query, params)
    }

    private fun retrieveKudos(query: String, params: Map<String, String>): Kudos? {
        var user: User? = null
        val tweets = ArrayList<Tweet>()

        driver.session().use { session ->
            val tx = session.beginTransaction()
            val result = tx.run(query, params)

            while (result.hasNext()) {
                val record = result.next()
                if (user == null) {
                    user = user(record.get("member"))
                }
                val sender = record . get("sender").get("screen_name").asString()
                tweets.add(tweet(record.get("tweet"), sender, record.get("tags").asList() as List<String>))
            }
            tx.success()
        }
        return user?.let { Kudos(user!!, tweets) }
    }

    override fun getRandom(): Kudos? {
        val query = """
match (kudos:Tag {name:"kudos"})
match (sender:User)-[:POSTED]->(tweet:Tweet:Content)-[:MENTIONED]-(member:User)
where not tweet:Retweet and tweet.created > timestamp()/1000 - 28*24*3600
with member, sender,tweet, (case when (tweet)-[:TAGGED]->(kudos) then 2 else 1 end) * (tweet.favorites + size((tweet)<-[:RETWEETED]-())) as score
order by score desc
with member, collect({sender:sender,tweet:tweet})[0..10] as messages, sum(score) as totalScore
where size(messages) >= 4
WITH *
order by totalScore desc limit 100
WITH member, messages ORDER BY rand() LIMIT 1
UNWIND messages as message
WITH message.sender as sender, message.tweet as tweet, member
return sender,tweet, member, [(tweet)-[:TAGGED]-(tag) | tag.name] as tags ORDER BY rand() LIMIT 4;
"""

        return retrieveKudos(query, emptyMap())
    }

    private fun user(u: Value) = User(
            screenName = u.get("screen_name").asString(),
            name = u.get("name").asString(),
            description = null,
            location = u.get("location").asString(),
            imageUrl = url(u.get("profile_image_url")),
            followers = u.get("followers").asInt(),
            following = u.get("following").asInt())

    private fun tweet(c: Value, sender: String, tags:List<String>) = Tweet(
            sender = sender,
            text = c.get("text").asString(),
            created = Date(c.get("created").asLong()),
            favorites = c.get("favorites").asInt(),
            hashTags = tags)

    private fun url(u: Value): URL? {
        return when {
            u.isEmpty -> null
            else -> URL(u.asString())
        }
    }

}


