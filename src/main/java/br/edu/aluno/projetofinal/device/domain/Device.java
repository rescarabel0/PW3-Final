package br.edu.aluno.projetofinal.device.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private String model;

    @Column
    @Nullable
    private String manufacturer;

    @Column
    @Nullable
    private DeviceType type;
}
