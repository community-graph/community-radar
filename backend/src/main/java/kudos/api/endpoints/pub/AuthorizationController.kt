package kudos.api.endpoints.pub


import kudos.api.service.authorization.AuthorizationService
import kudos.api.service.authorization.dto.CredentialsDTO
import org.springframework.web.bind.annotation.*


@RestController
class AuthorizationController(val authService: AuthorizationService) {

    @PostMapping("/authorization")
    fun authorize(@RequestBody credentials: CredentialsDTO) = authService.authorize(credentials)

}