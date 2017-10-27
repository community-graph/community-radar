package kudos

import kudos.infrastructure.persistence.ConnectionParameters
import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.Config
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.GraphDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PersistenceConfig {

    @Autowired lateinit var connectionParams: ConnectionParameters

    @Bean(destroyMethod = "close")
    fun driver(): Driver {
        return GraphDatabase.driver(connectionParams.databaseUri,
                AuthTokens.basic(connectionParams.userName, connectionParams.password),
                Config.build().withoutEncryption().toConfig())
    }
}