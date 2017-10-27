package kudos.api.service.user

import kudos.api.service.user.dto.UserDTO
import kudos.repositories.ogm.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class UserService constructor(private val userRepository: UserRepository)
{

    @Transactional
    fun getMyUser(profileId: String): UserDTO
    {
        val user = userRepository.findByUuid(profileId)!!
        user.lastActive = Date()
        userRepository.save(user)

        return UserDTO.fromEntity(user)
    }
}