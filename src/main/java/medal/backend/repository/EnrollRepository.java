package medal.backend.repository;

import medal.backend.entity.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepository extends JpaRepository<Enroll, Long> {
    @Query("select e from Enroll e join fetch e.pill join fetch e.alarm where e.alarm.morning is true and e.alarm.member.id=:memberId")
    List<Enroll> findMorningEnrolls(Long memberId);

    @Query("select e from Enroll e join fetch e.pill join fetch e.alarm where e.alarm.launch is true and e.alarm.member.id=:memberId")
    List<Enroll> findLaunchEnrolls(Long memberId);

    @Query("select e from Enroll e join fetch e.pill join fetch e.alarm where e.alarm.dinner is true and e.alarm.member.id=:memberId")
    List<Enroll> findDinnerEnrolls(Long memberId);

    @Query("select e from Enroll e join fetch e.pill join fetch e.alarm where e.alarm.member.id=:memberId")
    List<Enroll> findByMemberId(Long memberId);
}
