package br.com.panan.insert;

import br.com.panan.domain.appuser.AppUser;
import br.com.panan.domain.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InsertUser {

    private AppUserRepository appUSerRepository;

    @Autowired
    public InsertUser(AppUserRepository appUSerRepository) {
        this.appUSerRepository = appUSerRepository;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        AppUser appUser = new AppUser("admin", encoder.encode("123456789"));
        AppUser appUserDB = appUSerRepository.findByUsername(appUser.getUsername());
        boolean duplicate = false;

        if (appUserDB != null) {
            if ((appUser.getId() == null || appUser.getId() == 0)
                    && appUser.getUsername().equals(appUserDB.getUsername())) {
                duplicate = true;
            }
            if (appUser.getId() != null && appUser.getId() > 0 && appUser.getId().equals(appUserDB.getId())) {
                duplicate = true;
            }
        }
        if (!duplicate) {
            appUSerRepository.save(appUser);
        }

    }
}
