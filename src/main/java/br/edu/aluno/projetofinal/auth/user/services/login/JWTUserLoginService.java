package br.edu.aluno.projetofinal.auth.user.services.login;

import br.edu.aluno.projetofinal.auth.token.TokenService;
import br.edu.aluno.projetofinal.auth.token.models.Token;
import br.edu.aluno.projetofinal.auth.token.models.TokenPayload;
import br.edu.aluno.projetofinal.auth.user.UserAuthentication;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class JWTUserLoginService implements UserLoginService {
    @NonNull
    private final AuthenticationManager authenticationManager;
    @NonNull
    private final TokenService tokenService;

    public JWTUserLoginService(@NonNull AuthenticationManager authenticationManager, @NonNull TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public Token login(String login, String password) {
        var authentication = new UserAuthentication(login, password);
        authenticationManager.authenticate(authentication);
        return tokenService.getToken(
                new TokenPayload(login),
                86400000L
        );
    }
}
