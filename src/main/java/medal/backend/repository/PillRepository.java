package medal.backend.repository;

import medal.backend.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {

    Pill findByName(String pillName);
}
