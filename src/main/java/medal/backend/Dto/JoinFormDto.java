package medal.backend.Dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinFormDto {

    private Long id;
    private String loginId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String role; //manager or nonManager
    private String targetLoginId;

}
