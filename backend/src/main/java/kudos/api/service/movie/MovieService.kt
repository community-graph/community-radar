package kudos.api.service.movie

import kudos.api.service.movie.dto.GraphDTO
import kudos.api.service.movie.dto.MovieDTO
import kudos.domain.model.persistent.entities.Movie
import kudos.domain.model.persistent.entities.Person
import kudos.domain.model.persistent.entities.Role
import kudos.exception.NotFoundException
import kudos.repositories.MovieRepository
import kudos.repositories.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * TODO: Add user recommendations based on weak ties, triadic closures and so forth.
 */
@Service
class MovieService constructor(private val movieRepository: MovieRepository,
                               private val userRepository: UserRepository) {

    @Transactional(readOnly = true)
    fun graph(limit: Int) = GraphDTO.mapFromEntity(movieRepository.graph(limit))

    @Transactional(readOnly = true)
    fun findByTitle(title: String): MovieDTO {
        val movie = movieRepository.findByTitle(title) ?:
                throw NotFoundException("Movie with title $title does not exist.")
        return MovieDTO.fromEntity(movie)
    }

    @Transactional(readOnly = true)
    fun findByTitleContaining(term: String) =
            MovieDTO.mapFromEntities(movieRepository.findByTitleContaining(term))

    @Transactional
    fun addLikeInteractionTo(uuid: String, forUserUserUuid: String) {

        val movie = movieRepository.findByUuid(uuid) ?:
                throw NotFoundException("Movie with uuid $uuid does not exist.")
        val user = userRepository.findByUuid(forUserUserUuid) ?:
                throw NotFoundException("User with uuid $uuid does not exist.")

        user.addLikeInteraction(movie)
        user.lastActive = Date()
        userRepository.save(user)
    }

    @Transactional
    fun save(dto: MovieDTO): MovieDTO {
        val movie = Movie(title = dto.title, releasedYear = dto.releasedYear, tagLine = dto.tagLine)
        dto.roles.forEach {
            val person = Person(name = it.person.name, born = it.person.born)
            val role = Role(movie = movie, person = person)
            it.roles.forEach { roleName ->
                role.addRoleName(roleName)
            }
            movie.addRole(role)
        }
        //TODO: Check what happens to UUID on merge by title here
        movieRepository.save(movie)
        return MovieDTO.fromEntity(movie)
    }


}
