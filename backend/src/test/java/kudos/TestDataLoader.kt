package kudos

import kudos.domain.model.persistent.entities.User
import kudos.repositories.UserRepository
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