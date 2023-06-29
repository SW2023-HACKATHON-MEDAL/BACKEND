package medal.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medal.backend.Dto.JoinFormDto;
import medal.backend.Dto.LoginFormDto;
import medal.backend.entity.Member;
import medal.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    public Long saveMember(JoinFormDto joinFormDto) {
        Member member = Member.builder()
                .loginId(joinFormDto.getLoginId())
                .password(joinFormDto.getPassword())
                .userName(joinFormDto.getUserName())
                .phoneNumber(joinFormDto.getUserName())
                .build();
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
        return null;
    }


}
