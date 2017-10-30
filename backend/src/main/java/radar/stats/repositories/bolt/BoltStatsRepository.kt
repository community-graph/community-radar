package stats.repositories.bolt

import stats.repositories.api.StatsRepository

import org.neo4j.driver.v1.Driver
import org.springframework.stereotype.Repository

@Repository
class BoltStatsRepository(private val driver: Driver) : StatsRepository {

    override fun tweetsPerMonth(): List<StatsRepository.Monthly> {
        val query = """
match (n:Tweet) where exists(n.created)
return apoc.date.format(n.created,'s','yyyy-MM-dd') as month, count(*) as count
order by month asc
            """

        return driver.session().use { session ->
            session.run(query).list{ StatsRepository.Monthly(month = it.get("month",""), count = it.get("count",0)) }
        }
    }
}
