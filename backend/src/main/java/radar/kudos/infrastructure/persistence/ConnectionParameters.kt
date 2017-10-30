package radar.kudos.infrastructure.persistence

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ConnectionParameters @Autowired constructor(
        @Value("@{spring.data.neo4j.uri}") databaseUri: String?,
        @Value("@{spring.data.neo4j.username}") val userName: String?,
        @Value("@{spring.data.neo4j.password}") val password: String?)
{
    val databaseUri: String? = when
    {
        databaseUri == null -> null
        databaseUri.startsWith("@{") && databaseUri.endsWith("}") -> null
        else -> databaseUri
    }

}
