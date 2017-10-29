package kudos.api.service.kudos

import kudos.api.service.twitter.TwitterClient
import kudos.domain.model.persistent.entities.pojo.Kudos
import kudos.domain.model.persistent.entities.pojo.User
import kudos.repositories.bolt.BoltKudosRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.MalformedURLException
import java.net.URL

@Service
class KudosService(val kudosRepository: BoltKudosRepository,
                   val twitterClient: TwitterClient,
                   @Value("@{kudos.defaultImageUrl}") defaultImageUrl: String,
                   @Value("@{kudos.defaultDescription}") defaultDescription: String) {

    private val defaultImageUrl: URL
    private val defaultDescription: String

    init {
        try {
            this.defaultImageUrl = URL(defaultImageUrl)
        } catch (e: MalformedURLException) {
            throw IllegalStateException("A valid default image URL is required: $defaultImageUrl")
        }
        this.defaultDescription = defaultDescription
        if (this.defaultDescription.replace("@{kudos.defaultDescription}", "").isBlank()) {
            throw IllegalStateException("A default description is required: $defaultDescription")
        }
    }

    fun getRandom(): Kudos {
        val kudos = kudosRepository.getRandom()
        return when (kudos) {
            null -> throw IllegalArgumentException("No random kudos available.")
            else -> enrich(kudos)
        }
    }

    fun getByScreenName(id: String): Kudos {
        val kudos = kudosRepository.getByTwitterId(id)
        return when (kudos) {
            null -> throw IllegalArgumentException("No kudos for id $id")
            else -> enrich(kudos)
        }
    }

    /**
     * Add missing biography details, and a default image URL.
     */
    private fun enrich(kudos: Kudos): Kudos =
            kudos.copy(
                    communityMember = twitterClient.load(kudos.communityMember.screenName)
                            .withDefaultImageURL(defaultImageUrl)
                            .withDefaultDescription(defaultDescription),
                    tweets = kudos.tweets.map {
                        it.withDefaultHashTags(listOf("#kudos")).withMaxHashTags(2)
                    })

}


