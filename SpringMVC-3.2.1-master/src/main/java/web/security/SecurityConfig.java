package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@ComponentScan("web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    @Autowired
    private  SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

//    @Autowired
//    public SecurityConfig(@Qualifier("userDetServImpl") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
//        this.userDetailsService = userDetailsService;
//        this.successUserHandler = successUserHandler;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user_page").access("hasAnyRole('ROLE_USER')") // разрешаем входить на /user пользователям с ролью юзер
                .antMatchers("/login*", "/registration*").anonymous()         //анонимным пользователям доступ только на логин и регистр
                .antMatchers("/user").authenticated()                  //после аутентификации доступ только к странице пользователя
                .antMatchers("/admin**").access("hasRole('ROLE_ADMIN')").anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/user")                     //редирект пользователя из /логин на его страницу
                .and()
                .formLogin().loginPage("/login")                                        // форма для логина
                .loginProcessingUrl("/login")                                   //путь куда отправляем пользователя для обработки
                .usernameParameter("j_username")                                        // параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                .successHandler(successUserHandler)                                   // подключаем наш SuccessHandler для перенеправления по ролям
                .permitAll();                                                           // даем доступ к форме логина всем​

        http.logout().permitAll()                                                       // разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))      // указываем URL логаута
                .logoutSuccessUrl("/login?")                                      // указываем URL при удачном логауте
                .and().csrf().disable();                                                //выключаем кроссдоменную секьюр

    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
