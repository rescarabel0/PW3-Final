package br.edu.aluno.projetofinal.auth.user.provider;

import br.edu.aluno.projetofinal.auth.user.UserAuthentication;
import br.edu.aluno.projetofinal.auth.user.services.auth.UserAuthService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthProvider implements AuthenticationProvider {
    @NonNull
    private final UserAuthService userAuthService;

    public UserAuthProvider(@NonNull UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var auth = (UserAuthentication) authentication;
        var optionalUser = userAuthService.findUserByCredentials(auth.getLogin(), auth.getPassword());
        if (optionalUser.isEmpty()) return null;
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UserAuthentication.class);
    }
}
