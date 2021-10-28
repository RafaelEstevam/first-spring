/**
 * Classe de configuração do spring security
 */

package fatec.sp.gov.br.firstspring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fatec.sp.gov.br.firstspring.filter.AuthFilter;

@EnableWebSecurity //Habilitar configurações padrão do spring security
@EnableGlobalMethodSecurity(prePostEnabled = true) //Habilitar a segurança por anotação
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//Injeta o serviço que autentica o usuário. Neste projeto, o LoginService.

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //Desabilitar front dentro do Spring. A integração será via REST
        .addFilterBefore(new AuthFilter(), UsernamePasswordAuthenticationFilter.class) //Filtro customizado para validar o usuário
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //Limpar memória entre requisições
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * Como o spring faz a autenticação. Login
         * Pra fazer o login, vai usar o serviço
         */
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEnconderBean(){
        /**
         * Indica ao Spring security qual será o Encoder de senha usado
         * @Bean disponibiliza esta implementação para fazer o autowired dela. Usado em classes
         * que não se tem o controle.
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        /**
         * O spring não disponibiliza o método. É necessário dar um Override e também anota-lo para disponibilizar
         */
        return super.authenticationManagerBean();
    }
}
