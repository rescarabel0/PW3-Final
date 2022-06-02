package br.edu.aluno.projetofinal.auth.token;

import br.edu.aluno.projetofinal.auth.token.models.Token;
import br.edu.aluno.projetofinal.auth.token.models.TokenPayload;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface TokenService {
    @NonNull
    Token getToken(@NonNull TokenPayload tokenPayload, long expirationMs);

    @Nullable
    TokenPayload validateToken(@NonNull String token);
}
