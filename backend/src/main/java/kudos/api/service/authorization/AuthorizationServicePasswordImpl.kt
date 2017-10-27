package kudos.api.service.authorization;

import kudos.api.service.authorization.dto.AuthorizationDTO
import kudos.api.service.authorization.dto.CredentialsDTO
import kudos.exception.UnauthorizedException
import kudos.repositories.ogm.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class AuthorizationServicePasswordImpl (
        private val userRepository: UserRepository) : AuthorizationService
{

    @Transactional
    override fun authorize(request: CredentialsDTO): AuthorizationDTO
    {
        val user = userRepository.findByEmail(request.email)
        if (user == null || user.password != request.password)
        {
            throw UnauthorizedException("The provided request were not valid.")
        }
        return AuthorizationDTO.fromEntity(user)
    }
}
