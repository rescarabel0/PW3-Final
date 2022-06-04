package br.edu.aluno.projetofinal.device.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Device {
    @Id
    @Column
    @SequenceGenerator(name = "device_id_seq",
            sequenceName = "device_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "device_id_seq")
    @Nullable
    private Long id;

    @Column
    @Nullable
    @Setter
    private String model;

    @Column
    @Nullable
    @Setter
    private String manufacturer;

    @Column
    @Nullable
    @Setter
    private DeviceType type;
}
