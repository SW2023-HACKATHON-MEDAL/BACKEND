package medal.backend.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinFormDto {

    private Long id;
    @ApiModelProperty(value = "아이디(필요)")
    private String loginId;
    @ApiModelProperty(value = "비밀번호(필요)")
    private String password;
    @ApiModelProperty(value = "회원이름(필요)")
    private String userName;
    @ApiModelProperty(value = "전화번호(필요)")
    private String phoneNumber;
    @ApiModelProperty(value = "아이디(manager or nonManager)")
    private String role; //manager or nonManager
    @ApiModelProperty(value = "아이디(manager role 일시 필요)")
    private String targetLoginId;

}
