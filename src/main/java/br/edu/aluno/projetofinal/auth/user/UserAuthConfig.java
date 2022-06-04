package br.edu.aluno.projetofinal.auth.user;

import br.edu.aluno.projetofinal.auth.user.filter.UserJWTAuthFilter;
import br.edu.aluno.projetofinal.auth.user.provider.UserAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Getter
@Component
public class UserAuthConfig {
    @NonNull
    private final UserJWTAuthFilter userAuthFilter;
    @NonNull
    private final UserAuthProvider userAuthProvider;
}
