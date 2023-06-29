package medal.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmFromDto {
    private String pillName;
    private Boolean morning;
    private Boolean dinner;
    private Boolean launch;
    private Integer takeTime; //투악 횟수
}
