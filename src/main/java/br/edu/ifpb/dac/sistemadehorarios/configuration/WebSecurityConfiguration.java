package br.edu.ifpb.dac.sistemadehorarios.configuration;

import br.edu.ifpb.dac.sistemadehorarios.entity.User.UserService;
import br.edu.ifpb.dac.sistemadehorarios.service.AuthenticationService;
import br.edu.ifpb.dac.sistemadehorarios.service.AuthenticationTokenFilterService;
import br.edu.ifpb.dac.sistemadehorarios.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationTokenFilterService authenticationTokenFilterService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Configurações de autorização
        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/refreshToken").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Setando filtro de autenticação antes do filtro padrão do spring
                .and().addFilterBefore(authenticationTokenFilterService, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configurações de autenticacao
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) {
        // Configurações de recursos estáticos(js, css, imagens)
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configurations/**", "/swagger-resources/**");
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        List<String> all = Arrays.asList("*");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(all);
        corsConfiguration.setAllowedOriginPatterns(all);
        corsConfiguration.setAllowedHeaders(all);
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        CorsFilter corsFilter = new CorsFilter(source);

        FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(corsFilter);
        filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filter;
    }
}