package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.casadocodigo.loja.dao.UsuarioDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Aqui achei melhor deixar o detalhe dos produtos sem login pois 
		//não estou utilizando o usuário pra nada na finalização.
		http.authorizeRequests()
		.antMatchers("/produtos/form").hasRole("ADMIN")
		.antMatchers("/pedidos/**").hasRole("ADMIN")
		.antMatchers("/usuarios/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/produtos").hasRole("ADMIN")
		.antMatchers("/carrinho/**").permitAll()
		.antMatchers("/pagamento/**").permitAll()
		.antMatchers("/produtos/**").permitAll()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/relatorio-produtos/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn").permitAll()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	
    // Forma recomendada de ignorar no filtro de segurança as requisições para recursos estáticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
