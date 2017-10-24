package kudos.api.service.kudos.dto

import java.net.URL

data class CommunityMemberDTO(val name: String,
                              val imageUrl: URL,
                              val bio: String)