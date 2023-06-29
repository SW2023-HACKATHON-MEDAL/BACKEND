package medal.backend.repository;

import medal.backend.entity.Pill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PillRepository extends JpaRepository<Pill, Long> {
    @Query("select P from Pill p where p.member.id=:memberId and p.morning is true")
    List<Pill> findMorningPills(@Param("memberId") Long memberId);

    @Query("select P from Pill p where p.member.id=:memberId and p.dinner is true")
    List<Pill> findDinnerPills(@Param("memberId") Long memberId);

    @Query("select P from Pill p where p.member.id=:memberId and p.evening is true")
    List<Pill> findEveningPills(@Param("memberId") Long memberId);
}
