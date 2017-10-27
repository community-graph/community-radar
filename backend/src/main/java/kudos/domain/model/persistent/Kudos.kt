package kudos.domain.model.persistent

data class Kudos(val user: User,
                 val mentions: List<Tweet>)