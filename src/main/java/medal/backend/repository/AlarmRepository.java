package medal.backend.repository;

import medal.backend.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    @Modifying
    @Query("update Alarm a set a.morningAte = true where a.id in :keysToChange")
    void updateMorningAte(List<Long> keysToChange);

    @Modifying
    @Query("update Alarm a set a.launchAte = true where a.id in :keysToChange")
    void updateLaunchAte(List<Long> keysToChange);

    @Modifying
    @Query("update Alarm a set a.dinnerAte = true where a.id in :keysToChange")
    void updateDinnerAte(List<Long> keysToChange);
}
