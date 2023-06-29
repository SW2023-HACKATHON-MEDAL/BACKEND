package medal.backend.repository;

import medal.backend.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {
}
