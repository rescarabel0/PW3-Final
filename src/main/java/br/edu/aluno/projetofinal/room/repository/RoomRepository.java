package br.edu.aluno.projetofinal.room.repository;

import br.edu.aluno.projetofinal.room.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoomRepository extends JpaRepository<Room, Long> {
}
