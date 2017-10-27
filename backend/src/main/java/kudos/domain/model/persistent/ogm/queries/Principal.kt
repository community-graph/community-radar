

package kudos.domain.model.persistent.ogm.queries

import org.springframework.data.neo4j.annotation.QueryResult


@QueryResult
class Principal (var applicationToken: String,
                 var profileId: String)
{

    override fun toString(): String
    {
        if (applicationToken.isNullOrEmpty())
        {
            return "Empty Principal"
        } else
        {
            return "Principal {token: $applicationToken, uuid: $profileId"
        }
    }

}