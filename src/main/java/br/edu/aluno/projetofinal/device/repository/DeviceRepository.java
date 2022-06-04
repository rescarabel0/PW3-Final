package br.edu.aluno.projetofinal.device.repository;

import br.edu.aluno.projetofinal.device.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
