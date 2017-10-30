package kudos.api.service.kudos

import kudos.api.service.twitter.TwitterClient
import kudos.domain.model.persistent.entities.pojo.Kudos
import kudos.repositories.api.KudosRepository
import org.springframework.stereotype.Service

@Service
class KudosService(val kudosRepository: KudosRepository, val twitterClient: TwitterClient) {

    fun getRandom() =
            kudosRepository.getRandom()?.let { enrich(it) } ?: throw IllegalArgumentException("No random kudos available.")

    fun getByScreenName(id: String)
            = kudosRepository.getByTwitterId(id)?.let { enrich(it) } ?: throw IllegalArgumentException("No kudos for id $id")


    /**
     * Add missing biography details
     */
    private fun enrich(kudos: Kudos) =
            kudos.copy(communityMember = twitterClient.load(kudos.communityMember.screenName))
}


