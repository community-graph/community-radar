package kudos.api.endpoints.pub

import kudos.api.service.kudos.KudosService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kudos")
class KudosController constructor(private val kudosService: KudosService) {

    @GetMapping("/member/{memberId}")
    fun kudos(@PathVariable memberId : String) = kudosService.getByMemberId(memberId)

}

