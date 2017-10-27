package kudos.repositories.ogm


import kudos.domain.model.persistent.ogm.entities.User
import kudos.domain.model.persistent.ogm.queries.Principal
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.GraphRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : GraphRepository<User>
{

    fun findByUuid(id: String): User?

    @Cacheable("AuthorizationCache")
    @Query("MATCH (n:User {applicationToken: {0} }) RETURN n.uuid as profileId, n.applicationToken as applicationToken, n.roles as roles;")
    fun findByApplicationToken(token: String): Principal?

    fun findByEmail(email: String): User?

}

