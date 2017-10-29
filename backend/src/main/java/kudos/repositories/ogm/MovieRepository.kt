package kudos.repositories.ogm

import kudos.domain.model.persistent.entities.ogm.Movie
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface MovieRepository : PagingAndSortingRepository<Movie, String> {

    fun findByTitle(@Param("title") title: String): Movie?

    fun findByTitleContaining(title: String): Collection<Movie>

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
    fun graph(@Param("limit") limit: Int): Collection<Movie>
}

