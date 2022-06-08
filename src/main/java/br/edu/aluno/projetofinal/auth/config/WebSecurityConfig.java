package br.edu.aluno.projetofinal.auth.config;

import br.edu.aluno.projetofinal.auth.user.UserAuthConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings("deprecated")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @NonNull
    private final AuthEntryPoint authEntryPoint;

    @NonNull
    private final UserAuthConfig userAuthConfig;

    public WebSecurityConfig(@NonNull AuthEntryPoint authEntryPoint, @NonNull UserAuthConfig userAuthConfig) {
        this.authEntryPoint = authEntryPoint;
        this.userAuthConfig = userAuthConfig;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(userAuthConfig.getUserAuthProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers(HttpMethod.POST, "/auth/**").permitAll().and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/user/sign-up", "/user/sign-up/").permitAll()

                .anyRequest().authenticated();

        http.addFilterBefore(userAuthConfig.getUserAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }


}
