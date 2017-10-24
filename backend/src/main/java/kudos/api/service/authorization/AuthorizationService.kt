package kudos.api.service.authorization

import kudos.api.service.authorization.dto.AuthorizationDTO
import kudos.api.service.authorization.dto.CredentialsDTO

interface AuthorizationService
{
    fun authorize(request: CredentialsDTO): AuthorizationDTO
}