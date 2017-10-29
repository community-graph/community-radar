package kudos.domain.model.persistent.entities.pojo

import java.util.*

data class Tweet(val sender: String,
                 val text: String,
                 val created: Date,
                 val favorites: Int,
                 val hashTags: List<String>) {

    fun withDefaultHashTags(tags: List<String>) = when (this.hashTags.isEmpty()) {
        true -> this.copy(hashTags = tags)
        else -> this
    }

    fun withMaxHashTags(count: Int) = this.copy(hashTags = hashTags.take(count))

}