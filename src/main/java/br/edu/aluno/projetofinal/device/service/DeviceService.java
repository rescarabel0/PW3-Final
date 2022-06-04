package br.edu.aluno.projetofinal.device.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import org.springframework.lang.NonNull;

import java.util.List;

public interface DeviceService {
    @NonNull
    List<Device> findAll();
}
