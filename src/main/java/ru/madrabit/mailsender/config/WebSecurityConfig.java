package ru.madrabit.mailsender.config;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;

import static ru.madrabit.mailsender.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**", "/query/fp/checked/", "/query/fp/deps/")
                .hasAnyRole(ADMIN.name(), MANAGER.name())
                .and()
                .httpBasic();
        http.logout(logout -> logout
                .permitAll()
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                }));
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());

    }
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors();
//        http.csrf().disable() // TODO: I will teach this in detail in the next section
//                .authorizeRequests()
//                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                .antMatchers("/api/**, /departments").hasRole(STUDENT.name())
////                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
////                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
////                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
////                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      //   auth.userDetailsService(userDetailsService());
//
//        auth
//                .inMemoryAuthentication()
//                .withUser("1")
//                .password(passwordEncoder().encode("2"))
//                .roles("USER");
//    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails elina = User.builder()
                .username("elina")
                .password(passwordEncoder().encode("elina.123"))
                .authorities(MANAGER.getGrantedAuthorities())
                .build();

        UserDetails admin = User.builder()
                .username("1")
                .password(passwordEncoder().encode("2"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                elina
        );

    }

}


