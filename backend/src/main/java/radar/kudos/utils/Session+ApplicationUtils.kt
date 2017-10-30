package radar.kudos.utils

import org.neo4j.ogm.model.Result
import org.neo4j.ogm.session.Session

fun Session.query(query: String) : Result
{
    return this.query(query, mapOf<String, Any>())
}
