package radar.kudos.api.endpoints.pub

import radar.kudos.api.service.kudos.KudosService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kudos")
class KudosController constructor(private val kudosService: KudosService) {

    @GetMapping("/for/{screenName}")
    fun byScreenName(@PathVariable screenName: String) = kudosService.getByScreenName(screenName)

    @GetMapping("/random")
    fun kudos() = kudosService.getRandom()
}
