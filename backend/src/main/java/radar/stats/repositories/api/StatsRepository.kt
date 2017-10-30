package radar.stats.repositories.api

interface StatsRepository {

    data class Monthly(val month: String, val count: Int)

    fun tweetsPerMonth(): List<Monthly>
}
