package br.edu.aluno.projetofinal.auth.user;

import br.edu.aluno.projetofinal.auth.user.filter.UserJWTAuthFilter;
import br.edu.aluno.projetofinal.auth.user.provider.UserAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Getter
public class UserAuthConfig {
    @NonNull
    private final UserJWTAuthFilter userJWTAuthFilter;
    @NonNull
    private final UserAuthProvider userAuthProvider;
}
