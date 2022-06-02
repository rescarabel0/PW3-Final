package br.edu.aluno.projetofinal.auth.user.filter;

import br.edu.aluno.projetofinal.auth.JWTAuthFilter;
import br.edu.aluno.projetofinal.auth.token.TokenService;
import br.edu.aluno.projetofinal.auth.token.models.TokenPayload;
import br.edu.aluno.projetofinal.auth.user.UserAuthentication;
import br.edu.aluno.projetofinal.auth.user.services.UserAuthService;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserJWTAuthFilter extends JWTAuthFilter {
    @NonNull
    private final UserAuthService userAuthService;

    public UserJWTAuthFilter(@NonNull TokenService tokenService, @NonNull UserAuthService userAuthService) {
        super(tokenService);
        this.userAuthService = userAuthService;
    }

    @Override
    protected Authentication getAuthentication(TokenPayload tokenData, HttpServletRequest request) {
        var optionalFoundUser = userAuthService.findUserByCredentials(tokenData.getSubject(), null);
        if (optionalFoundUser.isEmpty()) return null;

        var user = optionalFoundUser.get();
        var login = user.getLogin();
        var password = user.getPassword();

        if (login == null || password == null) return null;
        return new UserAuthentication(login, password);
    }
}
