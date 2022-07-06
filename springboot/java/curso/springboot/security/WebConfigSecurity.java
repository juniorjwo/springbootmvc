package curso.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebConfigSecurity  extends WebSecurityConfigurerAdapter{
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	
	
	@Override //Configura as solicitações de acesso por HTTP
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()// Desativa as configuraçoes padrões de memoria do spring
		.authorizeRequests() // permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() // qualquer usuario acessa a pagina inicial do sistema
		.antMatchers(HttpMethod.GET, "/cadastropessoa").hasAnyRole("ADMIN") // PODE TIRAR O ROLE_ QUE O SPRING ENTENDE
		.antMatchers("**/materialize/**").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // permitir qualquer usuario ao formulario de login, cria um form login padrao
		.loginPage("/login")
		.defaultSuccessUrl("/cadastropessoa")
		.failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login") // mapeia url de logout e invalida usuario autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override // cria autenticacao do usuario com o banco de dados ou em memoria
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		// Autenticacao em memoria
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//		.withUser("admin")
//		.password("$2a$10$IPFzAZ6n/qB1fVvlcSwEZuFfwwzj0cuVZ8L.xtUVWHeJG6hMNn8OS")
//		.roles("ADMIN");
	}
	
	@Override // ignora url especificas
	public void configure(WebSecurity web) throws Exception {

	          web.ignoring().antMatchers("/materialize/**");
	         

	}



}
