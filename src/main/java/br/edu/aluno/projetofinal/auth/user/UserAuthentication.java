package br.edu.aluno.projetofinal.auth.user;

import lombok.Getter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Objects;

@Getter
public class UserAuthentication extends AbstractAuthenticationToken {
    @NonNull
    private final String login;

    @NonNull
    private final String password;


    public UserAuthentication(@NonNull String login, @NonNull String password) {
        super(null);
        this.login = login;
        this.password = password;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAuthentication that = (UserAuthentication) o;
        return login.equals(that.login) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password);
    }
}
