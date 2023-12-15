package com.mercadolivro.config

import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val customerRepository: CustomerRepository,
    private val authenticationManager: AuthenticationManager
){

    private val PUBLIC_POST_MATCHERS = arrayOf(
        "/customers", "/book"
    )
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }


    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .authorizeHttpRequests{
            authorizeRequest ->
                authorizeRequest.requestMatchers(HttpMethod.GET,*PUBLIC_POST_MATCHERS).permitAll()
                authorizeRequest.anyRequest().authenticated()

            }
            .addFilter(AuthenticationFilter(authenticationManager, customerRepository))
            .sessionManagement{ session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}
            .httpBasic(Customizer.withDefaults());

        return httpSecurity.build()
    }



}