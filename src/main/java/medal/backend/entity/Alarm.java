package medal.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alarm {
    @Id
    @GeneratedValue
    @Column(name = "alarm_id")
    private Long id;
    @Builder.Default
    private Boolean morning = false;
    @Builder.Default
    private Boolean dinner = false;
    @Builder.Default
    private Boolean launch = false;
    @Builder.Default
    private Boolean morningAte = false;
    @Builder.Default
    private Boolean dinnerAte = false;
    @Builder.Default
    private Boolean launchAte = false;
    private Integer remainingDay; //남은 복용 기간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
