package radar.kudos.repositories.ogm

import radar.kudos.domain.model.persistent.entities.ogm.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface PersonRepository : PagingAndSortingRepository<Person, String>
