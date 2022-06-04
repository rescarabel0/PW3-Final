package br.edu.aluno.projetofinal.device.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.device.repository.DeviceRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MissingRequestValueException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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

    @Override
    public Optional<Device> update(@NonNull Device device, @NonNull Long deviceId) throws Throwable {
        var optionalFoundDevice = deviceRepository.findById(deviceId);
        if (optionalFoundDevice.isEmpty()) throw new EntityNotFoundException();

        var foundDevice = optionalFoundDevice.get();

        foundDevice.setManufacturer(
                Optional.ofNullable(device.getManufacturer()).orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Dispositivo inválido. Falta fabricante."))
        );
        foundDevice.setModel(
                Optional.ofNullable(device.getModel()).orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Dispositivo inválido. Falta modelo."))
        );
        foundDevice.setType(
                Optional.ofNullable(device.getType()).orElseThrow((Supplier<Throwable>) () -> new MissingRequestValueException("Dispositivo inválido. Falta tipo."))
        );

        return Optional.of(deviceRepository.save(foundDevice));
    }

    @Override
    public void deleteByID(@NonNull Long deviceId) throws EntityNotFoundException {
        var optionalFoundDevice = deviceRepository.findById(deviceId);
        if (optionalFoundDevice.isEmpty()) throw new EntityNotFoundException();
        deviceRepository.delete(optionalFoundDevice.get());
    }
}
