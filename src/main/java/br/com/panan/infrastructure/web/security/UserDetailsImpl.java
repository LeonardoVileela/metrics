package br.com.panan.infrastructure.web.security;

import br.com.panan.domain.appuser.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//transforma meu appUser em UserDatails para que eu posso fazer a comparacao e achar o ususario no banco
public class UserDetailsImpl implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Long id;
	

	public UserDetailsImpl(AppUser appUser) {
		this.username = appUser.getUsername();
		this.password = appUser.getPassword();
		this.id = appUser.getId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.NO_AUTHORITIES;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	//configura��es abaixo s�o para conta expiradas ou bloqueadas ou algo do tipo
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public Long getId() {
		return id;
	}

}
