package br.edu.aluno.projetofinal.auth;

import br.edu.aluno.projetofinal.auth.token.TokenService;
import br.edu.aluno.projetofinal.auth.token.models.TokenPayload;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public abstract class JWTAuthFilter extends AuthFilter<TokenPayload> {
    @NonNull
    private final TokenService tokenService;

    public JWTAuthFilter(@NonNull TokenService tokenService) {
        super();
        this.tokenService = tokenService;
    }

    @Nullable
    @Override
    protected TokenPayload validateToken(@NonNull String token) {
        return tokenService.validateToken(token);
    }
}
