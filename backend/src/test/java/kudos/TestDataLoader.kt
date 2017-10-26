package kudos

import kudos.domain.model.persistent.entities.User
import kudos.repositories.UserRepository
import org.neo4j.ogm.session.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

/**
 * Create a test user for integration tests, until sign up feature implemented.
 */
@Component
class TestDataLoader(private val userRepo: UserRepository) {

    init {
        this.addTestUser()
    }

    @Autowired lateinit var session : Session

    @Transactional
    fun addTestUser() {

        val email = "jasper@appsquick.ly"
        val jasper = userRepo.findByEmail(email)
        if (jasper == null) {
            userRepo.save(User(
                    firstName = "Jasper",
                    lastName = "Blues",
                    email = email,
                    password = "password"))
        }
    }

}