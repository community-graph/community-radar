package kudos.domain.model.persistent

import java.util.*

data class Tweet(val text: String,
                 val created: Date,
                 val favorites: Int)