package kudos.api.service.user.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import kudos.domain.model.persistent.entities.User
import kudos.api.service.EntityDTOMapper


@JsonInclude(Include.NON_NULL)
data class UserDTO(
        val uuid: String,
        val firstName: String,
        val lastName: String,
        val email: String) {

    companion object : EntityDTOMapper<User, UserDTO> {

        override fun fromEntity(entity: User) = UserDTO(
                uuid = entity.uuid,
                firstName = entity.firstName,
                lastName = entity.lastName,
                email = entity.email)
    }
}



