package medal.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import medal.backend.entity.Pill;

@Data
@NoArgsConstructor
@Builder
public class PillDto {
    private Long pillId;
    private String name; // 이름
    private String color; // 색깔
    private String shape; // 모양
    private String texture; // 제형
    private String storeImgName; //사진 (로컬에 저장된 이름)
    private Long alarmId; //먹고 체크할 때 사용

    public PillDto(Long pillId, String name, String color, String shape, String texture, String storeImgName, Long alarmId) {
        this.pillId = pillId;
        this.name = name;
        this.color = color;
        this.shape = shape;
        this.texture = texture;
        this.storeImgName = storeImgName;
        this.alarmId = alarmId;
    }
}