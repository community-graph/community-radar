

package kudos.infrastructure.security

import kudos.repositories.ogm.UserRepository
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken

class TokenAuthenticationProvider : AuthenticationProvider
{
    val profileRepo: UserRepository
    val apiKey: String


    constructor(profileRepo: UserRepository, apiKey: String)
    {
        this.profileRepo = profileRepo
        this.apiKey = apiKey
    }


    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication
    {
        val credentials: SessionCredentials? = authentication.credentials as SessionCredentials?
        if (credentials != null && credentials.apiKey == this.apiKey)
        {
            val auth = profileRepo.findByApplicationToken(credentials.accessToken)
            if (auth != null)
            {
                val newAuthentication = PreAuthenticatedAuthenticationToken(auth, credentials, emptyList())
                newAuthentication.isAuthenticated = true
                return newAuthentication
            }
        }
        throw BadCredentialsException("Bad credentials given.")
    }

    override fun supports(authentication: Class<*>): Boolean
    {
        return authentication == PreAuthenticatedAuthenticationToken::class.java
    }
}
