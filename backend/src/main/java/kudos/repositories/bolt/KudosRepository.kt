package kudos.repositories.bolt

import kudos.domain.model.persistent.entities.pojo.Kudos

interface KudosRepository {

    fun getByTwitterId(twitterId: String): Kudos?

    fun getRandom(): Kudos?

}