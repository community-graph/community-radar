package radar.kudos.domain.model.persistent.entities.pojo

import java.net.URL

data class User(val screenName: String,
                val name: String,
                val description: String?,
                val location: String?,
                val imageUrl: URL?,
                val followers: Int,
                val following: Int)
