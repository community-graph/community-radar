package kudos.api.service.twitter

import kudos.domain.model.persistent.entities.pojo.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import java.net.URL


@Service
class TwitterClient(@Value("@{twitter.consumerKey}") val consumerKey: String,
                    @Value("@{twitter.consumerSecret}") val consumerSecret: String,
                    @Value("@{twitter.accessKey}") val accessKey: String,
                    @Value("@{twitter.accessSecret}") val accessSecret: String) {

    lateinit var twitter: Twitter
        protected set

    init {
        val config = ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessKey)
                .setOAuthAccessTokenSecret(accessSecret)
                .build()
        twitter = TwitterFactory(config).instance
    }


    fun load(screenName: String): User {
        val result: twitter4j.User? = twitter.showUser(screenName)
        return when (result) {
            null -> throw IllegalArgumentException("No data for screenName: $screenName")
            else -> User(
                    screenName = result.screenName,
                    name = result.name,
                    description = result.description,
                    location = result.location,
                    imageUrl = URL(result.originalProfileImageURL),
                    followers = result.followersCount,
                    following = result.friendsCount)
        }

    }

}