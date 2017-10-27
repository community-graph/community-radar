package kudos.api.service.movie.dto

import kudos.api.service.EntityDTOMapper
import kudos.domain.model.persistent.ogm.entities.Person

data class PersonDTO(val name: String,
                     val born: Long) {

    companion object : EntityDTOMapper<Person, PersonDTO> {

        override fun fromEntity(entity: Person) =
                PersonDTO(name = entity.name, born = entity.born)
    }
}

