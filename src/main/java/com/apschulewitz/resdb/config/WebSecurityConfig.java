package com.apschulewitz.resdb.config;

import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.security.controller.UserAuthenticationProvider;
import com.apschulewitz.resdb.security.UserAuthenticationService;
import com.apschulewitz.resdb.security.filter.JwtTokenAuthenticationFilter;
import com.apschulewitz.resdb.security.filter.SecurityAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;


@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private String secret;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    private CsrfTokenRepository jwtCsrfTokenRepository;

    @Value(("${client.cors.base.url}"))
    private String clientCorsBaseUrl;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        log.info("Registering authentication provider {}", this.userAuthenticationProvider.getClass().getSimpleName());
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Configuring http security to use secret, CORS and CSRF");

        String secret = secret();

        log.info("Secret: " + secret);

        log.info("Adding matcher for '/'");
        log.info("Adding matcher for '/auth/**'");
        log.info("Adding matcher for '{}/**'", RestUrlPaths.RESEARCH_DATABASE_BASE_URL);
        log.info("Adding matcher for '/stomp/**'");

//        http
//            .csrf()
//            .csrfTokenRepository(jwtCsrfTokenRepository)
//            .disable()
//            .addFilterAfter(jwtTokenAuthenticationFilter("/**", secret), ExceptionTranslationFilter.class)
//            .addFilterAfter(corsFilter(), ExceptionTranslationFilter.class)
//            .exceptionHandling()
//            .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
//            .accessDeniedHandler(new RestAccessDeniedHandler())
//            .and()
//            .anonymous()
//            .and()
//            .sessionManagement().sessionCreationPolicy(STATELESS)
//            .and()
//            .authorizeRequests()
//            .antMatchers("/").permitAll()
//            .antMatchers("/auth/**").permitAll()
//            .antMatchers(RestUrlPaths.RESEARCH_DATABASE_BASE_URL + "/**").permitAll()
//            .antMatchers("/stomp/**").permitAll()
//            .anyRequest().authenticated();

        // try very basic auth
        http.httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint()) //.realmName("resdb")
                //.accessDeniedHandler(new RestAccessDeniedHandler())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .authenticationProvider(userAuthenticationProvider)
                .authorizeRequests()
                    .antMatchers("/**").permitAll().anyRequest().authenticated();
    }
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new SecurityAuthenticationEntryPoint();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
//        authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
//    }

//    @Bean
//    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
//        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(adDomain, adUrl);
//        provider.setConversionSubErrorCodesToExceptions(true);
//        provider.setUseAuthenticationRequestCredentials(true);
//        return provider;
//    }

    @Bean
    String secret() {
        String secret = null;
//        synchronized (this) {
            try {
//                if (StringUtils.isEmpty(secret)) {
                    secret = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("secret.key"),
                            Charset.defaultCharset());
//                }
            } catch (IOException e) {
                log.error("Error reading secret key: " + e.getMessage(), e);
            }
//        }

        if (StringUtils.isEmpty(secret)) {
                log.error("Error reading secret key. Value is null or empty");
        }

        return secret;
    }

    @Bean
    public HeaderHttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    public JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter(String path, String secret) {
        return new JwtTokenAuthenticationFilter(userAuthenticationService, path, secret);
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                    .withUser("adrian").password("nepal1").roles("USER");
//    }

    @Bean
    public CorsFilter corsFilter() {
        log.info("Configuring CORS filter");
        log.info("Setting allowed headers {}", Arrays.asList(ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION).toString());
        log.info("Setting allowed methods {}", Arrays.asList(GET, PUT, POST, OPTIONS, PATCH));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowCredentials(true);

        configuration.addAllowedOrigin("*");

        configuration.addAllowedHeader(ORIGIN);
        configuration.addAllowedHeader(CONTENT_TYPE);
        configuration.addAllowedHeader(ACCEPT);
        configuration.addAllowedHeader(AUTHORIZATION);

        configuration.addAllowedMethod(GET);
        configuration.addAllowedMethod(PUT);
        configuration.addAllowedMethod(POST);
        configuration.addAllowedMethod(OPTIONS);
        configuration.addAllowedMethod(DELETE);
        configuration.addAllowedMethod(PATCH);

        configuration.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
////                registry.addMapping("/resdb/api/auth/hello").allowedOrigins(clientCorsBaseUrl);
//                registry.addMapping(clientCorsBaseUrl + RestUrlPaths.TEST_PAGE_URL).allowedOrigins(clientCorsBaseUrl);
//                registry.addMapping(clientCorsBaseUrl + RestUrlPaths.LOGIN_PAGE_URL).allowedOrigins("/**");
//            }
//        };
//    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
    public String generateSecretKey() {

        // On Unix like OS, NativePRNG algorithm, is being returned, which is self-seeded with non-blocking (file://dev/urandom) source of entropy.
        SecureRandom secureRandomGenerator = null;

        try {
            secureRandomGenerator = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            log.error("Error instantiating secure random generator: " + e.getMessage(), e);
            return null;
        }

        byte[] randomBytes = new byte[20];
        secureRandomGenerator.nextBytes(randomBytes);
        return new String(randomBytes);
    }

}
