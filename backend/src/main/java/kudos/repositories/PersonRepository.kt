package kudos.repositories

import kudos.domain.model.persistent.entities.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonRepository : PagingAndSortingRepository<Person, Long>
