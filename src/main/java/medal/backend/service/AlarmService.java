package medal.backend.service;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.AlarmFromDto;
import medal.backend.entity.Alarm;
import medal.backend.entity.Enroll;
import medal.backend.entity.Pill;
import medal.backend.repository.AlarmRepository;
import medal.backend.repository.EnrollRepository;
import medal.backend.repository.PillRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final EnrollRepository enrollRepository;
    private final PillRepository pillRepository;

    private void enrollAlarm(AlarmFromDto alarmFromDto) {
        Pill pill = pillRepository.findByName(alarmFromDto.getPillName());
        Alarm.builder()
                        .
        Enroll.builder()
    }
}
