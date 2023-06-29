package medal.backend.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginFormDto {
    @ApiModelProperty(value = "아이디(필요)")
    private String loginId;
    @ApiModelProperty(value = "비밀번호(필요)")
    private String password;
}
