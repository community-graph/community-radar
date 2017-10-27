package kudos.domain.model.persistent.ogm.entities


import java.util.ArrayList

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship


@NodeEntity
class Person(@GraphId var id: Long? = null,
             @Index(unique = true, primary = true) var name: String,
             var born: Long,
             @Relationship(type = "ACTED_IN") var movies: List<Movie> = ArrayList())
