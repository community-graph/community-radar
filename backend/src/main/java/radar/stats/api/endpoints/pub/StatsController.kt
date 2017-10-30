package stats.api.endpoints.pub

import stats.repositories.api.StatsRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stats")
class StatsController constructor(private val repo: StatsRepository) {

    @GetMapping("/tweets/month")
    fun twitterStats() = repo.tweetsPerMonth()
}

