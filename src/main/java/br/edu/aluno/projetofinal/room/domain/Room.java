package br.edu.aluno.projetofinal.room.domain;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {
    @Id
    @Column
    @SequenceGenerator(name = "room_id_seq",
            sequenceName = "room_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "room_id_seq")
    @Nullable
    private Long id;

    @Column
    @Nullable
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "room")
    @Nullable
    @Setter
    private List<Device> devices;

    @JoinColumn
    @ManyToOne
    @Nullable
    @Setter
    private User user;
}
