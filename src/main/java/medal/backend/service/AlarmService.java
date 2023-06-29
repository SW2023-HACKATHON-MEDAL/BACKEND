package medal.backend.service;

import lombok.RequiredArgsConstructor;
import medal.backend.Dto.AlarmFromDto;
import medal.backend.Dto.PillDto;
import medal.backend.entity.Alarm;
import medal.backend.entity.Enroll;
import medal.backend.entity.Member;
import medal.backend.entity.Pill;
import medal.backend.repository.AlarmRepository;
import medal.backend.repository.EnrollRepository;
import medal.backend.repository.MemberRepository;
import medal.backend.repository.PillRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final EnrollRepository enrollRepository;
    private final PillRepository pillRepository;
    private final MemberRepository memberRepository;

    /**
     * 받은 약 이름으로 각 회원 아이디에 알람 등록
     */
    public void enrollAlarm(AlarmFromDto alarmFromDto, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(EntityNotFoundException::new);

        Pill pill = pillRepository.findByName(alarmFromDto.getPillName());

        double takeTimeInDay  = 0;
        if(alarmFromDto.getMorning() == true) takeTimeInDay++;
        if(alarmFromDto.getLaunch() == true) takeTimeInDay++;
        if(alarmFromDto.getDinner() == true) takeTimeInDay++;

        Alarm alarm = Alarm.builder()
                .morning(alarmFromDto.getMorning())
                .launch(alarmFromDto.getLaunch())
                .dinner(alarmFromDto.getDinner())
                .remainingDay((int) Math.ceil(alarmFromDto.getTakeTime() / takeTimeInDay))
                .member(member)
                .build();

        Enroll enroll = Enroll.builder()
                .alarm(alarm)
                .pill(pill)
                .build();

        alarmRepository.save(alarm);
        enrollRepository.save(enroll);
    }

    /**
     * 아침,점심,저녁 등록에 해당하는 pillDto 가져오기
     */
    public List<PillDto> findPills(int when, Long memberId) {
        List<Enroll> enrollList = new ArrayList<>();
        //아침
        if(when == 0) {
            enrollList = enrollRepository.findMorningEnrolls(memberId);
        }
        else if(when == 1) {
            enrollList = enrollRepository.findLaunchEnrolls(memberId);
        }
        else if(when == 2) {
            enrollList = enrollRepository.findDinnerEnrolls(memberId);
        }

        List<PillDto> pillDtoList = new ArrayList<>();
        for(Enroll enroll : enrollList) {
            Pill pill = enroll.getPill();
            PillDto pillDto = PillDto.builder()
                    .pillId(pill.getId())
                    .name(pill.getName())
                    .color(pill.getColor())
                    .shape(pill.getShape())
                    .texture(pill.getTexture())
                    .storeImgName(pill.getStoreImgName())
                    .build();
            pillDtoList.add(pillDto);
        }
        return pillDtoList;
    }

}
