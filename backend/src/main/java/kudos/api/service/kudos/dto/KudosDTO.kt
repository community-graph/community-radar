package kudos.api.service.kudos.dto

data class KudosDTO(val communityMember: CommunityMemberDTO,
                    val tweets: List<TweetDTO>)