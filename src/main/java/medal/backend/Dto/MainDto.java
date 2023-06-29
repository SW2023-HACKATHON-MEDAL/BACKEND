package medal.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainDto {
    private List<PillDto> morningPills;
    private List<PillDto> dinnerPills;
    private List<PillDto> launchPills;
}
