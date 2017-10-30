package kudos.domain.model.persistent.entities.ogm

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import org.neo4j.ogm.annotation.EndNode
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.RelationshipEntity
import org.neo4j.ogm.annotation.StartNode
import java.util.*

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
@RelationshipEntity(type = "ACTED_IN")
class Role(@Id var id: Long? = null,
           @StartNode var person: Person,
           @EndNode var movie: Movie,
           var roles: ArrayList<String> = ArrayList()) {

    fun addRoleName(name: String) {
        this.roles.add(name)
    }
}
