package medal.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medal.backend.Dto.JoinFormDto;
import medal.backend.Dto.LoginFormDto;
import medal.backend.Dto.ManageInfoDto;
import medal.backend.entity.Alarm;
import medal.backend.entity.Enroll;
import medal.backend.entity.Member;
import medal.backend.repository.EnrollRepository;
import medal.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final EnrollRepository enrollRepository;

    /**
     * 회원가입
     */
    public Long saveMember(JoinFormDto joinFormDto) {
        Member member = Member.builder()
                .loginId(joinFormDto.getLoginId())
                .password(joinFormDto.getPassword())
                .userName(joinFormDto.getUserName())
                .phoneNumber(joinFormDto.getUserName())
                .role(joinFormDto.getRole())
                .build();
        
        Member managedMember = null;
        //관계를 맺는 경우
        if(joinFormDto.getTargetLoginId() != null) {
            managedMember = memberRepository.findByLoginId(joinFormDto.getTargetLoginId());
            if(managedMember == null) log.info("없는 회원과 관계를 맺으려 시도");
            member.setManagedMember(managedMember); // 관리할 회원과 연관관계 설정
        }

        return memberRepository.save(member).getId();
    }

    /**
     * 로그인 처리
     */
    public Member loginMember(LoginFormDto loginFormDto) {
        Member member = memberRepository.findByLoginId(loginFormDto.getLoginId());
        if(member == null) {
            log.info("없는 아이디입니다.");
        }
        if(loginFormDto.getPassword().equals(loginFormDto.getPassword())) {
            return member;
        }
        log.info("비밀번호가 틀립니다.");
        return null;
    }


    public ManageInfoDto findManagedMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(EntityNotFoundException::new);

        //관리되는 회원
        Member managedMember = memberRepository.findById(member.getManagedMember().getId())
                .orElseThrow(EntityNotFoundException::new);

        LocalTime currentTime = LocalTime.now();
        System.out.println("현재시각: " + currentTime);

        List<Enroll> enrollList = enrollRepository.findByMemberId(managedMember.getId());


        ManageInfoDto manageInfoDto = new ManageInfoDto();

        if (currentTime.isAfter(LocalTime.of(10, 0))) {
            log.info("아침 지남");
            Boolean check = true;
            for(Enroll enroll : enrollList) {
                if (enroll.getAlarm().getMorningAte() == false) {
                    check = false;
                    manageInfoDto.setIsAteMorning(false);
                }
            }
            if(check) manageInfoDto.setIsAteMorning(true);
        }
        if (currentTime.isAfter(LocalTime.of(14, 0))) {
            log.info("점심 지남");
            Boolean check = true;
            for(Enroll enroll : enrollList) {
                if (enroll.getAlarm().getLaunchAte() == false) {
                    manageInfoDto.setIsAteLaunch(false);
                }
            }
            if(check) manageInfoDto.setIsAteLaunch(true);
        }
        if (currentTime.isAfter(LocalTime.of(21, 0))) {
            log.info("저녁 지남");
            Boolean check = true;
            for(Enroll enroll : enrollList) {
                if (enroll.getAlarm().getDinnerAte() == false) {
                    manageInfoDto.setIsAteDinner(false);
                }
            }
            if(check) manageInfoDto.setIsAteDinner(true);
        }

        return manageInfoDto;
    }
}
