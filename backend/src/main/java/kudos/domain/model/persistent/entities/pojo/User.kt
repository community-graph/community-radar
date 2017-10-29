package kudos.domain.model.persistent.entities.pojo

import java.net.URL

data class User(val screenName: String,
                val name: String,
                val description: String?,
                val location: String?,
                val imageUrl: URL?,
                val followers: Int,
                val following: Int)

{
    /**
     * Add the specified image URL, if currently null.
     */
    fun withDefaultImageURL(url: URL) = when {
        this.imageUrl == null -> this.copy(imageUrl = url)
        else -> this
    }

    /**
     * Add the specified default description, if currently empty.
     */
    fun withDefaultDescription(default: String) = when {
        this.description.isNullOrEmpty() -> this.copy(description = default)
        else -> this
    }

}