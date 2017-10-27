package kudos.api.service.movie.dto

import kudos.api.service.EntityDTOMapper
import kudos.domain.model.persistent.ogm.entities.Role

data class RoleDTO(val person: PersonDTO,
                   val roles: Collection<String>) {

    companion object : EntityDTOMapper<Role, RoleDTO> {

        override fun fromEntity(entity: Role): RoleDTO =
                RoleDTO(person = PersonDTO.fromEntity(entity.person), roles = entity.roles)


    }
}

