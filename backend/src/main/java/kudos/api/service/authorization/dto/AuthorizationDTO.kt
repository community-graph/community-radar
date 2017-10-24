package kudos.api.service.authorization.dto

import kudos.api.service.EntityDTOMapper
import kudos.domain.model.persistent.entities.User

data class AuthorizationDTO(
        val accessToken: String,
        val profileId: String) {

    companion object : EntityDTOMapper<User, AuthorizationDTO> {

        override fun fromEntity(entity: User) = AuthorizationDTO(
                accessToken = entity.applicationToken!!,
                profileId = entity.uuid)

    }
}



