package br.com.panan.infrastructure.web.security;

import br.com.panan.domain.appuser.AppUser;
import br.com.panan.domain.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//classe para buscar e comparar o usuario e caso ele nao exista retorna uma exception
@Service
public class UserDetailsServiceimpl implements UserDetailsService {

	private AppUserRepository appUserRepository;

	@Autowired
	public UserDetailsServiceimpl(AppUserRepository appUserRepository) {

		this.appUserRepository = appUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = this.appUserRepository.findByUsername(username);

		if (appUser == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(appUser);

	}

}
