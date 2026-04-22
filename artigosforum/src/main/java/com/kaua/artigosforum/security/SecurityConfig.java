package com.kaua.artigosforum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration = essa classe é lida pelo Spring na inicialização
// e registra os @Beans que estiverem dentro dela.
@Configuration

// @EnableWebSecurity = ativa o módulo de segurança web,
// substituindo o comportamento automático do Spring Security.
// Sem isso, ele continua usando a configuração padrão (que pede login).
@EnableWebSecurity
public class SecurityConfig {

    // @Bean = registra esse método como componente gerenciado pelo Spring.
    // O Spring vai chamar esse método automaticamente e guardar o resultado
    // para usar sempre que precisar de um SecurityFilterChain.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Desabilita CSRF (Cross-Site Request Forgery).
                // CSRF protege formulários HTML tradicionais com cookies.
                // Em APIs REST que usarão JWT no futuro, isso não é necessário
                // porque cada requisição já carrega o token de autenticação.
                .csrf(csrf -> csrf.disable())

                // Define a política de sessão como STATELESS (sem estado).
                // Isso significa que o servidor NÃO vai guardar sessão de usuário.
                // Cada requisição precisa ser autenticada por si só (via JWT futuramente).
                // É o padrão correto para APIs REST.
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Define as regras de quem pode acessar o quê.
                .authorizeHttpRequests(auth -> auth

                        // Por enquanto libera TUDO para qualquer requisição,
                        // autenticada ou não. Isso é TEMPORÁRIO.
                        // Quando implementarmos JWT, vamos trocar por regras como:
                        // .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        // .requestMatchers("/api/autor/**").authenticated()
                        // etc.
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}