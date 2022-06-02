package br.edu.aluno.projetofinal.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @Column
    @SequenceGenerator(name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_id_seq")
    @Nullable
    private Long id;

    @Nullable
    @Column
    private String name;

    @Nullable
    @Column
    private String login;

    @Nullable
    @Column
    private String password;

    @Nullable
    @Column
    private Time routineStartsAt;

    @Nullable
    @Column
    private Time routineEndsAt;
}
