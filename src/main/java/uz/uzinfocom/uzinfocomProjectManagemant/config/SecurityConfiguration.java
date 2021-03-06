package uz.uzinfocom.uzinfocomProjectManagemant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import uz.uzinfocom.uzinfocomProjectManagemant.Repository.MyUserRepository;
import uz.uzinfocom.uzinfocomProjectManagemant.Service.CustomUserDetailsService;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = MyUserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return true;
            }
        });
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/get/**", "/rest/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin().permitAll().and().formLogin().successForwardUrl("/get/projects");
    }

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        };
    }
}
