package br.edu.aluno.projetofinal.auth.token.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Token {
    @NonNull
    private final String value;

    @NonNull
    private final Date expireDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token that = (Token) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
