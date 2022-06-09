package br.edu.aluno.projetofinal.room.repository;

import br.edu.aluno.projetofinal.room.domain.Room;
import br.edu.aluno.projetofinal.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomRepository extends JpaRepository<Room, Long> {
    @NonNull
    List<Room> findAllByUser(@NonNull User user);
}
