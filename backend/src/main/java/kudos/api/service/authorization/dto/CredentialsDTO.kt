package kudos.api.service.authorization.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CredentialsDTO(
        val email: String,
        val password: String)



