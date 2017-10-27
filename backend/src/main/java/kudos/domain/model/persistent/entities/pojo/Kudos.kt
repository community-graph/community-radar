package kudos.domain.model.persistent.entities.pojo

data class Kudos(val communityMember: User,
                 val tweets: List<Tweet>)