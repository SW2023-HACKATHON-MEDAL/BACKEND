package medal.backend.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManageInfoDto {
    private Boolean isAteMorning = null;
    private Boolean isAteLaunch = null;
    private Boolean isAteDinner = null;
}
