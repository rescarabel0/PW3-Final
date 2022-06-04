package br.edu.aluno.projetofinal.device.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.device.repository.DeviceRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBDeviceService implements DeviceService {
    @NonNull
    private final DeviceRepository deviceRepository;

    public DBDeviceService(@NonNull DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
