package radar.kudos.repositories.api

import radar.kudos.domain.model.persistent.entities.pojo.Kudos

interface KudosRepository {

    fun getByTwitterId(twitterId: String): Kudos?

    fun getRandom(): Kudos?

}
