package kudos.api.service.movie.dto

import kudos.api.service.EntityDTOMapper
import kudos.domain.model.persistent.entities.Movie

class MovieDTO(val title: String,
               val uuid: String? = null,
               val releasedYear: Long,
               val tagLine: String?,
               val roles: Collection<RoleDTO>) {

    companion object : EntityDTOMapper<Movie, MovieDTO> {

        override fun fromEntity(entity: Movie) = MovieDTO(
                title = entity.title,
                uuid = entity.uuid,
                releasedYear = entity.releasedYear,
                tagLine = entity.tagLine,
                roles = RoleDTO.mapFromEntities(entity.roles))
    }

}


