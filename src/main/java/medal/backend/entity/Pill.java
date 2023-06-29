package medal.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pill {
    @Id
    @GeneratedValue
    @Column(name = "pill_id")
    private Long id;
    private String name; // 이름
    private String color; // 색깔
    private String shape; // 모양
    private String texture; // 제형

    private boolean morning = false;
    private boolean dinner = false;
    private boolean evening = false;

//    private LocalDateTime startDay; //시작 날
//    private LocalDateTime finishDay; //종료 날
    private Boolean ate = false;

    private String storeImgName; //사진 (로컬에 저장된 이름)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
