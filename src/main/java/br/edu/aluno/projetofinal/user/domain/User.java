package br.edu.aluno.projetofinal.user.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "app_user", indexes = @Index(name = "app_user_login_idx", columnList = "login"))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {
    @Id
    @Column
    @SequenceGenerator(name = "app_user_id_seq",
            sequenceName = "app_user_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "app_user_id_seq")
    @Nullable
    private Long id;

    @Nullable
    @Column
    @Setter
    private String name;

    @Nullable
    @Column
    @Setter
    private String login;

    @Nullable
    @Column
    @Setter
    private String password;

    @Nullable
    @Column
    @Setter
    private Time routineStartsAt;

    @Nullable
    @Column
    @Setter
    private Time routineEndsAt;
}
