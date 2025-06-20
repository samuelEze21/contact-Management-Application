//package com.semicolon.contact.Management.App.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//        @Bean
//        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
////        http.formLogin(withDefaults());
//            http.httpBasic(withDefaults());
//            return http.build();
//        }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1= User.withUsername("user1")
//                .password("{noop}password1")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("user1")
//                .password("{noop}adminPass")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1, admin);
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeHttpRequests()
////                .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin();
////
////        return http.build();
////
////
////    @Bean
////    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
////        return http.getSharedObject(AuthenticationManagerBuilder.class)
////                .userDetailsService(userDetailsService)
////                .passwordEncoder(passwordEncoder())
////                .and()
////                .build();
////    }
//}
