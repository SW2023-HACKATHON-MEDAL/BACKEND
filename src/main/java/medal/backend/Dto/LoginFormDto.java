package medal.backend.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginFormDto {
    private String loginId;
    private String password;
}
