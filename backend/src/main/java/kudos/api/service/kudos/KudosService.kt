package kudos.api.service.kudos

import kudos.api.service.kudos.dto.CommunityMemberDTO
import kudos.api.service.kudos.dto.KudosDTO
import kudos.api.service.kudos.dto.TweetDTO
import org.springframework.stereotype.Service
import java.net.URL

@Service
class KudosService {

    fun getByMemberId(id: String): KudosDTO {

        return KudosDTO(
                communityMember = CommunityMemberDTO(
                        "Andrey Breslav",
                        URL("https://pbs.twimg.com/profile_images/858532228275601409/687Zdk1H.jpg"),
                        "Lead Language Designer of Kotlin @ JetBrains"),
                tweets = listOf(
                        TweetDTO("@appsquickly", "Fun at KotlinKonf. One two three four five six. Seven Eight. The " +
                                "quick brown fox jumped over the lazy dogs. One two three four.", listOf("#kudos")),
                        TweetDTO("@appsquickly", "Fun at KotlinKonf. One two three four five six. Seven Eight. The " +
                                "quick brown fox jumped over the lazy dogs. One two three four.", listOf("#kudos")),
                        TweetDTO("@appsquickly", "Fun at KotlinKonf. One two three four five six. Seven Eight. The " +
                                "quick brown fox jumped over the lazy dogs. One two three four.", listOf("#kudos")),
                        TweetDTO("@appsquickly", "Fun at KotlinKonf. One two three four five six. Seven Eight. The " +
                                "quick brown fox jumped over the lazy dogs. One two three four.", listOf("#kudos"))
                ))
    }

}