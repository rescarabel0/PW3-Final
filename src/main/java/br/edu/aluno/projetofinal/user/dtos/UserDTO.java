package br.edu.aluno.projetofinal.user.dtos;

import br.edu.aluno.projetofinal.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDTO {
    @NonNull
    private String name;

    @NonNull
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String routineStartsAt;

    @NonNull
    private String routineEndsAt;

    @NonNull
    public User toDomain() {
        return new User(null, this.name, this.login, this.password, this.routineStartsAt, this.routineEndsAt);
    }
}
