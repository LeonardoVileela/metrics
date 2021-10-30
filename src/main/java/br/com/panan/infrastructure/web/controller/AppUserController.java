package br.com.panan.infrastructure.web.controller;

import br.com.panan.domain.appuser.AppUser;
import br.com.panan.domain.appuser.AppUserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppUserController {

	private final AppUserRepository appUserRepository;

	public AppUserController(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	@PostMapping("/cadaster")
	AppUser novoCadastro(@RequestBody AppUser appUser) {
		appUser.setUsername(appUser.getUsername().toLowerCase().trim());
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		appUser.setPassword(encoder.encode(appUser.getPassword()));
		return appUserRepository.save(appUser);
	}


}
