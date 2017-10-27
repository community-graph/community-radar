package kudos.domain.model.persistent

import java.net.URL

data class User(val screeName: String,
                val name: String,
                val location: String,
                val imageUrl: URL,
                val followers: Int,
                val following: Int)

