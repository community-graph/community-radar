package kudos.api.endpoints.secured

import kudos.api.endpoints.aspects.Authenticated
import kudos.api.service.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/mine")
    fun getMyProfile(@Authenticated profileId: String) = userService.getMyUser(profileId)

}