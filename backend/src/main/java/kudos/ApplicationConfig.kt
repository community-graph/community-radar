package kudos

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.neo4j.driver.v1.AuthTokens
import org.neo4j.driver.v1.Config
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.GraphDatabase
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer



@SpringBootApplication
@EntityScan("kudos.domain.model.persistent.entities.ogm")
class ApplicationConfig {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ApplicationConfig::class.java, *args)
        }
    }

    @Bean
    fun kotlinPropertyConfigurer(): PropertySourcesPlaceholderConfigurer {
        val propertyConfigurer = PropertySourcesPlaceholderConfigurer()
        propertyConfigurer.setPlaceholderPrefix("@{")
        propertyConfigurer.setPlaceholderSuffix("}")
        propertyConfigurer.setIgnoreUnresolvablePlaceholders(true)
        return propertyConfigurer
    }

    @Bean
    fun defaultPropertyConfigurer() = PropertySourcesPlaceholderConfigurer()


    @Bean
    fun mapperConfigurer() = Jackson2ObjectMapperBuilder().apply {
        serializationInclusion(JsonInclude.Include.NON_NULL)
        failOnUnknownProperties(true)
        featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        indentOutput(true)
        modules(listOf(KotlinModule()))
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurerAdapter() {
            override fun addCorsMappings(registry: CorsRegistry?) {
                registry!!.addMapping("/**").allowedOrigins("*")
            }
        }
    }

}


