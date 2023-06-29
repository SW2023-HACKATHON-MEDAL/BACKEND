package medal.backend.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManageInfoDto {
    private Boolean isAteMorning = true;
    private Boolean isAteLaunch = true;
    private Boolean isAteDinner = true;
}
