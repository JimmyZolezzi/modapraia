package moda.praia.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import moda.praia.modulo.clientes.ClienteBusiness;
import moda.praia.modulo.clientes.bean.Cliente;
import moda.praia.uteis.Criptografia;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger log = Logger.getLogger(CustomUserDetailsService.class);;

	@Autowired
	private ClienteBusiness clienteBusiness;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = clienteBusiness.obterClientePorEmail(email);
		
		if(cliente==null){
			log.info("User not found");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
		//String senhaCriptografada = Criptografia.criptografaSenha(senha);
		return new org.springframework.security.core.userdetails.User(cliente.getEmail(), cliente.getSenha(), 
                 true, true, true, true, getGrantedAuthorities(cliente));

	}

	private List<GrantedAuthority> getGrantedAuthorities(Cliente cliente) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
		return authorities;
	}

}
