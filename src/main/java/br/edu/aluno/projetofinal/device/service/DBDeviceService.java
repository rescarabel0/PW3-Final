package br.edu.aluno.projetofinal.device.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import br.edu.aluno.projetofinal.device.repository.DeviceRepository;
import br.edu.aluno.projetofinal.room.service.RoomService;
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
    @NonNull
    private final RoomService roomService;

    public DBDeviceService(@NonNull DeviceRepository deviceRepository, @NonNull RoomService roomService) {
        this.deviceRepository = deviceRepository;
        this.roomService = roomService;
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
    public Optional<Device> save(Device device) throws Throwable {
        return Optional.of(deviceRepository.save(device));
    }

    @Override
    public void deleteByID(@NonNull Long deviceId) throws EntityNotFoundException, MissingRequestValueException {
        var optionalFoundDevice = deviceRepository.findById(deviceId);
        if (optionalFoundDevice.isEmpty()) throw new EntityNotFoundException();
        var device = optionalFoundDevice.get();
        if (device.getRoom() != null) {
            roomService.removeFromDeviceList(device.getRoom(), device);
        }
        deviceRepository.delete(optionalFoundDevice.get());
    }
}
