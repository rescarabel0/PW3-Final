package br.edu.aluno.projetofinal.device.service;

import br.edu.aluno.projetofinal.device.domain.Device;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.MissingRequestValueException;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface DeviceService {
    @NonNull
    List<Device> findAll();

    @NonNull
    Optional<Device> update(@NonNull Device device, @NonNull Long deviceId) throws Throwable;

    @NonNull
    Optional<Device> save(@NonNull Device device) throws Throwable;

    @NonNull
    void deleteByID(@NonNull Long deviceId) throws EntityNotFoundException, MissingRequestValueException;
}
