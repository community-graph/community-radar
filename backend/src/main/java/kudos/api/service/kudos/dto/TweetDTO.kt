package kudos.api.service.kudos.dto

data class TweetDTO(val sender: String,
                    val text: String,
                    val hashTags: List<String>)