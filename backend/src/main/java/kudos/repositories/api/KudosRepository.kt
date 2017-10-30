package kudos.repositories.api

import kudos.domain.model.persistent.entities.pojo.Kudos

interface KudosRepository {

    fun getByTwitterId(twitterId: String): Kudos?

    fun getRandom(): Kudos?

}
