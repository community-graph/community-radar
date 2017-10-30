package radar.kudos.domain.model.persistent.entities.pojo

import java.util.*

data class Tweet(val sender: String,
                 val text: String,
                 val created: Date,
                 val favorites: Int,
                 val hashTags: List<String>)
