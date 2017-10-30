package radar.stats.api.endpoints.pub

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import radar.stats.repositories.api.StatsRepository

@RestController
@RequestMapping("/stats")
class StatsController constructor(private val repo: StatsRepository) {

    @GetMapping("/tweets/month")
    fun twitterStats() = repo.tweetsPerMonth()
}

